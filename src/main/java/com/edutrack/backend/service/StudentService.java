package com.edutrack.backend.service;

import com.edutrack.backend.dto.StudentDto;
import com.edutrack.backend.entity.Student;
import com.edutrack.backend.entity.User;
import com.edutrack.backend.exception.BadRequestException;
import com.edutrack.backend.exception.ResourceNotFoundException;
import com.edutrack.backend.repository.StudentRepository;
import com.edutrack.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public List<StudentDto> getAllStudents(String search, String category, String teacherEmail) {
        List<Student> students = studentRepository.searchStudents(search, category, teacherEmail);
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return mapToDto(student);
    }

    @Transactional
    public StudentDto createStudent(StudentDto dto) {
        if (studentRepository.existsByRollNo(dto.getRollNo())) {
            throw new BadRequestException("Roll no already exits");
        }

        String email = dto.getEmail();
        String password = dto.getPassword();

        if (email == null || email.isBlank()) {
            String firstName = dto.getName().trim().split("\\s+")[0].toLowerCase().replaceAll("[^a-z]", "");
            email = firstName + "." + dto.getRollNo() + "@edutrack.com";
        }

        if (password == null || password.isBlank()) {
            password = UUID.randomUUID().toString().substring(0, 8);
        }

        Student student = Student.builder()
                .rollNo(dto.getRollNo())
                .name(dto.getName())
                .studentClass(dto.getStudentClass())
                .attendance(dto.getAttendance() != null ? dto.getAttendance() : 0)
                .marks(dto.getMarks() != null ? dto.getMarks() : 0)
                .email(email)
                .password(password)
                .status("active")
                .teacherEmail(dto.getTeacherEmail())
                .build();

        Student savedStudent = studentRepository.save(student);

        // Also create student user credentials for login
        if (!userRepository.existsByEmail(email)) {
            User user = User.builder()
                    .name(savedStudent.getName())
                    .email(email)
                    .password(password)
                    .role("student")
                    .status("active")
                    .build();
            userRepository.save(user);
        }

        return mapToDto(savedStudent);
    }

    @Transactional
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        if (dto.getRollNo() != null && !dto.getRollNo().equals(student.getRollNo())) {
            if (studentRepository.existsByRollNoAndIdNot(dto.getRollNo(), id)) {
                throw new BadRequestException("Roll no already exits");
            }
            student.setRollNo(dto.getRollNo());
        }

        if (dto.getName() != null) student.setName(dto.getName());
        if (dto.getStudentClass() != null) student.setStudentClass(dto.getStudentClass());
        if (dto.getAttendance() != null) student.setAttendance(dto.getAttendance());
        if (dto.getMarks() != null) student.setMarks(dto.getMarks());
        if (dto.getTeacherEmail() != null) student.setTeacherEmail(dto.getTeacherEmail());
        if (dto.getStatus() != null) {
            student.setStatus(dto.getStatus());
            // Sync status to user account
            userRepository.findByEmail(student.getEmail()).ifPresent(user -> {
                user.setStatus(dto.getStatus());
                userRepository.save(user);
            });
        }

        Student updatedStudent = studentRepository.save(student);

        // Update corresponding User record name if changed
        if (dto.getName() != null) {
            userRepository.findByEmail(student.getEmail()).ifPresent(user -> {
                user.setName(dto.getName());
                userRepository.save(user);
            });
        }

        return mapToDto(updatedStudent);
    }

    @Transactional
    public StudentDto updateStudentStatus(Long id, String status) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setStatus(status);
        Student updatedStudent = studentRepository.save(student);

        userRepository.findByEmail(student.getEmail()).ifPresent(user -> {
            user.setStatus(status);
            userRepository.save(user);
        });

        return mapToDto(updatedStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        if (student.getEmail() != null) {
            userRepository.findByEmail(student.getEmail()).ifPresent(user -> userRepository.deleteByEmail(user.getEmail()));
        }

        studentRepository.delete(student);
    }

    private StudentDto mapToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId().toString())
                .rollNo(student.getRollNo())
                .name(student.getName())
                .studentClass(student.getStudentClass())
                .attendance(student.getAttendance())
                .marks(student.getMarks())
                .email(student.getEmail())
                .password(student.getPassword())
                .status(student.getStatus())
                .teacherEmail(student.getTeacherEmail())
                .build();
    }
}
