package com.example.eyeapplication;

import androidx.annotation.NonNull;

public class SchoolLevel {

    int id;
    String level;
    String subject;

    public SchoolLevel(int id, String level, String subject) {
        this.id = id;
        this.level = level;
        this.subject = subject;
    }

    public SchoolLevel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @NonNull
    @Override
    public String toString() {
        return subject;
    }
}
