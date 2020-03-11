package com.example.eyeapplication;

public class Homework {

    int id;
    int teacherId;
    String name;
    String section;
    String subject;
    int schoolId;

    public Homework(int teacherId, String name, String section, String subject, int schoolId) {
        this.teacherId = teacherId;
        this.name = name;
        this.section = section;
        this.subject = subject;
        this.schoolId = schoolId;
    }

    public Homework() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}
