package com.edutrack.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDto {

    private String id;
    private String rollNo;
    private String name;

    @JsonProperty("class")
    private String studentClass;

    private Integer attendance;
    private Integer marks;
    private String email;
    private String password;
    private String status;
    private String teacherEmail;

    public StudentDto() {
    }

    public StudentDto(String id, String rollNo, String name, String studentClass, Integer attendance, Integer marks, String email, String password, String status, String teacherEmail) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.studentClass = studentClass;
        this.attendance = attendance;
        this.marks = marks;
        this.email = email;
        this.password = password;
        this.status = status;
        this.teacherEmail = teacherEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public static StudentDtoBuilder builder() {
        return new StudentDtoBuilder();
    }

    public static class StudentDtoBuilder {
        private String id;
        private String rollNo;
        private String name;
        private String studentClass;
        private Integer attendance;
        private Integer marks;
        private String email;
        private String password;
        private String status;
        private String teacherEmail;

        public StudentDtoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public StudentDtoBuilder rollNo(String rollNo) {
            this.rollNo = rollNo;
            return this;
        }

        public StudentDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentDtoBuilder studentClass(String studentClass) {
            this.studentClass = studentClass;
            return this;
        }

        public StudentDtoBuilder attendance(Integer attendance) {
            this.attendance = attendance;
            return this;
        }

        public StudentDtoBuilder marks(Integer marks) {
            this.marks = marks;
            return this;
        }

        public StudentDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public StudentDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public StudentDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public StudentDtoBuilder teacherEmail(String teacherEmail) {
            this.teacherEmail = teacherEmail;
            return this;
        }

        public StudentDto build() {
            return new StudentDto(id, rollNo, name, studentClass, attendance, marks, email, password, status, teacherEmail);
        }
    }
}
