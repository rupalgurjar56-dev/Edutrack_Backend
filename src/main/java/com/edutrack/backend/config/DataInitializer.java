package com.edutrack.backend.config;

import com.edutrack.backend.entity.Student;
import com.edutrack.backend.entity.User;
import com.edutrack.backend.repository.StudentRepository;
import com.edutrack.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public DataInitializer(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        // Seed default teacher user if not exists
        if (!userRepository.existsByEmail("teacher@demo.com")) {
            User teacher = User.builder()
                    .name("Demo Teacher")
                    .email("teacher@demo.com")
                    .password("password123")
                    .role("teacher")
                    .status("active")
                    .build();
            userRepository.save(teacher);
        }

        // Seed sample students linked to teacher@demo.com if database is empty
        if (studentRepository.count() == 0) {
            createSampleStudent("101", "Rahul Sharma", "12th", 85, 92, "rahul.101@edutrack.com", "pass101", "teacher@demo.com");
            createSampleStudent("102", "Priya Verma", "12th", 70, 88, "priya.102@edutrack.com", "pass102", "teacher@demo.com");
            createSampleStudent("103", "Aman Gupta", "11th", 90, 95, "aman.103@edutrack.com", "pass103", "teacher@demo.com");
            createSampleStudent("104", "Sneha Patel", "10th", 65, 78, "sneha.104@edutrack.com", "pass104", "teacher@demo.com");
        }
    }

    private void createSampleStudent(String rollNo, String name, String studentClass, int attendance, int marks, String email, String password, String teacherEmail) {
        Student student = Student.builder()
                .rollNo(rollNo)
                .name(name)
                .studentClass(studentClass)
                .attendance(attendance)
                .marks(marks)
                .email(email)
                .password(password)
                .status("active")
                .teacherEmail(teacherEmail)
                .build();
        studentRepository.save(student);

        User studentUser = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role("student")
                .status("active")
                .build();
        userRepository.save(studentUser);
    }
}
