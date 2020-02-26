package com.example.eyeapplication;

public class Teacher {

    private Integer id;
    private Integer userId;
    private Integer schoolId;
    private String name;
    private String subject;
    private String sections;
    private String usernameT;
    private String passT;

    public Teacher(String name, String subject, String sections, String username, String pass) {
        this.name = name;
        this.subject = subject;
        this.sections = sections;
        this.usernameT = username;
        this.passT = pass;
    }

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public String getUsername() {
        return usernameT;
    }

    public void setUsername(String username) {
        this.usernameT = username;
    }

    public String getPass() {
        return passT;
    }

    public void setPass(String pass) {
        this.passT = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
