package com.example.eyeapplication;

public class Member {
    private String name;
    private String parentID;
    private String mail;
    private int pass;
    private int REpASS;

    public Member() {
    }

    public Member(String name, String mail, String ids) {
        this.name = name;
        this.mail = mail;
        this.parentID = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    /*public int getREpASS() {
        return REpASS;
    }*/

    /*public void setREpASS(int REpASS) {
        this.REpASS = REpASS;
    }*/
}
