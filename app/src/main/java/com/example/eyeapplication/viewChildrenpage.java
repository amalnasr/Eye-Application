package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eyeapplication.database.DatabaseStatements;

public class viewChildrenpage extends AppCompatActivity {

    String studentNumber;
    StudentInformation studentInformation;

    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_childrenpage);

        if (getIntent().hasExtra("studentNumber"))
            studentNumber = getIntent().getExtras().getString("studentNumber");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        studentInformation = databaseStatements.getStudentsByNumber(studentNumber);

        name = findViewById(R.id.textView5);
        name.setText(studentInformation.getName());
    }

    public void chDuti(View view) {
        Intent intent = new Intent(viewChildrenpage.this,subjectActivity.class);
        intent.putExtra("type",0);
        intent.putExtra("studentNumber",studentNumber);
        startActivity(intent);
    }

    public void rate(View view) {
        Intent intent = new Intent(viewChildrenpage.this,subjectActivity.class);
        intent.putExtra("type",1);
        intent.putExtra("studentNumber",studentNumber);
        startActivity(intent);
    }

    public void back(View view) {
        Intent inten = new Intent( viewChildrenpage.this, parenthomepage.class);
        startActivity(inten);
    }
}
