package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eyeapplication.database.DatabaseStatements;

public class showHomeWork extends AppCompatActivity {

    String studentNumber,subject;
    StudentInformation studentInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_home_work);

        if (getIntent().hasExtra("studentNumber"))
            studentNumber = getIntent().getExtras().getString("studentNumber");

        if (getIntent().hasExtra("subject"))
            subject = getIntent().getExtras().getString("subject");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        studentInformation = databaseStatements.getStudentsByNumber(studentNumber);

        Homework homework = databaseStatements.getSubjectHomeWorkForStudent(subject,studentInformation.getSec());

        if (homework != null){
            TextView textView = findViewById(R.id.title);
            textView.setText(homework.getName());
        }
    }

    public void back(View view) {
        Intent inten = new Intent( showHomeWork.this, subjectActivity.class);
        startActivity(inten);
    }
}
