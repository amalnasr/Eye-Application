package com.example.eyeapplication;

public class HomeworkAnswer {

    int id;
    int homeWorkId;
    int teacherId;
    int studentId;
    String answer;
    String section;
    int schoolId;

    public HomeworkAnswer(int homeWorkId, int teacherId, int studentId,
                          String answer, String section, int schoolId) {
        this.homeWorkId = homeWorkId;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.answer = answer;
        this.section = section;
        this.schoolId = schoolId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeWorkId() {
        return homeWorkId;
    }

    public void setHomeWorkId(int homeWorkId) {
        this.homeWorkId = homeWorkId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}
