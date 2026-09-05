package com.edutrack.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String rollNo;

    @Column(nullable = false)
    private String name;

    @Column(name = "student_class", nullable = false)
    private String studentClass;

    private Integer attendance;

    private Integer marks;

    @Column(unique = true)
    private String email;

    private String password;

    private String status = "active";

    @Column(name = "teacher_email")
    private String teacherEmail;

    public Student() {
    }

    public Student(Long id, String rollNo, String name, String studentClass, Integer attendance, Integer marks, String email, String password, String status, String teacherEmail) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.studentClass = studentClass;
        this.attendance = attendance;
        this.marks = marks;
        this.email = email;
        this.password = password;
        if (status != null) {
            this.status = status;
        }
        this.teacherEmail = teacherEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private Long id;
        private String rollNo;
        private String name;
        private String studentClass;
        private Integer attendance;
        private Integer marks;
        private String email;
        private String password;
        private String status = "active";
        private String teacherEmail;

        public StudentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StudentBuilder rollNo(String rollNo) {
            this.rollNo = rollNo;
            return this;
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder studentClass(String studentClass) {
            this.studentClass = studentClass;
            return this;
        }

        public StudentBuilder attendance(Integer attendance) {
            this.attendance = attendance;
            return this;
        }

        public StudentBuilder marks(Integer marks) {
            this.marks = marks;
            return this;
        }

        public StudentBuilder email(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder password(String password) {
            this.password = password;
            return this;
        }

        public StudentBuilder status(String status) {
            this.status = status;
            return this;
        }

        public StudentBuilder teacherEmail(String teacherEmail) {
            this.teacherEmail = teacherEmail;
            return this;
        }

        public Student build() {
            return new Student(id, rollNo, name, studentClass, attendance, marks, email, password, status, teacherEmail);
        }
    }
}
