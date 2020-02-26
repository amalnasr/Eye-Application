package com.example.eyeapplication;

public class StudentInformation {
    private Integer id;
    private Integer schoolId;
    private String name, motherid, fatherid, studentid, level, section;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMid() {
        return motherid;
    }

    public void setMid(String mid) {
        this.motherid = mid;
    }

    public String getFid() {
        return fatherid;
    }

    public void setFid(String fid) {
        this.fatherid = fid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String id) {
        this.studentid = id;
    }

    public String getLev() {
        return level;
    }

    public void setLev(String lev) {
        this.level = lev;
    }

    public String getSec() {
        return section;
    }

    public void setSec(String sec) {
        this.section = sec;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public StudentInformation() {

    }
}

