package com.example.eyeapplication.notification;

public class Notification {

    Integer id;
    Integer teacherId;
    Integer studentId;
    Integer schoolId;
    String title;
    Integer status;

    public Notification(Integer teacherId, Integer studentId, Integer schoolId, String title) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.title = title;
    }

    public Notification() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
