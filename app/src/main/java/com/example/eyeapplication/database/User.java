package com.example.eyeapplication.database;

public class User {

    Integer id;
    String name;
    String email;
    String identityId;
    String pw;
    int type;

    public User(String name, String email, String identityId, String pw, int type) {
        this.name = name;
        this.email = email;
        this.identityId = identityId;
        this.pw = pw;
        this.type = type;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
