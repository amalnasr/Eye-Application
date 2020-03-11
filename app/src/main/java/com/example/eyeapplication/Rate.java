package com.example.eyeapplication;

public class Rate {

    int id;
    int teacherId;
    String subject;
    int studentId;
    String readCat;
    String writeCat;
    String saveCat;
    String homeworkCat;

    public Rate(int teacherId, String subject, int studentId, String readCat, String writeCat, String saveCat, String homeworkCat) {
        this.teacherId = teacherId;
        this.subject = subject;
        this.studentId = studentId;
        this.readCat = readCat;
        this.writeCat = writeCat;
        this.saveCat = saveCat;
        this.homeworkCat = homeworkCat;
    }

    public Rate() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getReadCat() {
        return readCat;
    }

    public void setReadCat(String readCat) {
        this.readCat = readCat;
    }

    public String getWriteCat() {
        return writeCat;
    }

    public void setWriteCat(String writeCat) {
        this.writeCat = writeCat;
    }

    public String getSaveCat() {
        return saveCat;
    }

    public void setSaveCat(String saveCat) {
        this.saveCat = saveCat;
    }

    public String getHomeworkCat() {
        return homeworkCat;
    }

    public void setHomeworkCat(String homeworkCat) {
        this.homeworkCat = homeworkCat;
    }
}
