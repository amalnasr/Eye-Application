package com.example.eyeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyeapplication.database.DatabaseStatements;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class submitDuties extends AppCompatActivity {
    EditText input;

    int userId = -1;
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_duties);

        input=findViewById(R.id.editText8);

        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        teacher = databaseStatements.getTeacher(userId);

    }
    public void send(View view) {
        if (TextUtils.isEmpty(input.getText().toString().trim())){
            Toast.makeText(submitDuties.this," من فضلك ادخل نص الواجب", Toast.LENGTH_LONG).show();
            return;
        }

        Homework homework = new Homework(teacher.getId(),input.getText().toString().trim(),
                teacher.getSections(),teacher.getSubject(),teacher.getSchoolId());
        DatabaseStatements statements = new DatabaseStatements(submitDuties.this);
        statements.newHomework(homework);

        Toast.makeText(submitDuties.this," تم إرسال الواجب", Toast.LENGTH_LONG).show();
        finish();
    }
    public void back(View view) {
        Intent inten = new Intent( submitDuties.this, teacherhomepage.class);
        startActivity(inten);
    }
}
