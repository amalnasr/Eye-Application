package com.example.eyeapplication;

public class School {
    private Integer id;
    private String Name;
    private String Classname;
    private String Classnumber;


    public School() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setClassname(String classname) {
        Classname = classname;
    }

    public String getClassname() {
        return Classname;
    }

    public void setClassnumber(String classnumber) {
        Classnumber = classnumber;
    }

    public String getClassnumber() {
        return Classnumber;
    }
}
