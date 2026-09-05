package com.edutrack.backend.repository;

import com.edutrack.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRollNo(String rollNo);
    Optional<Student> findByEmail(String email);
    boolean existsByRollNo(String rollNo);
    boolean existsByRollNoAndIdNot(String rollNo, Long id);

    @Query("SELECT s FROM Student s WHERE " +
           "(:teacherEmail IS NULL OR :teacherEmail = '' OR s.teacherEmail = :teacherEmail) AND " +
           "(:search IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:category IS NULL OR :category = '' OR s.studentClass = :category)")
    List<Student> searchStudents(@Param("search") String search, 
                                @Param("category") String category, 
                                @Param("teacherEmail") String teacherEmail);
}
