package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyeapplication.database.DatabaseStatements;

import java.util.List;

public class SchoolDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView1, recyclerView2;

    TeachersRvAdapter teachersRvAdapter;
    List<Teacher> teacherList;

    StudentsRvAdapter studentsRvAdapter;
    List<StudentInformation> studentInformations;

    DatabaseStatements statements;

    int schoolId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);

        if (getIntent().hasExtra("schoolId"))
            schoolId = getIntent().getExtras().getInt("schoolId");

        statements = new DatabaseStatements(this);
        recyclerView1 = findViewById(R.id.rv1);
        recyclerView2 = findViewById(R.id.rv2);

        setupRv();

    }

    private void setupRv() {
        teacherList = statements.getTeachers(schoolId);
        studentInformations = statements.getStudents(schoolId);

        if (teachersRvAdapter == null) {
            recyclerView1.setLayoutManager(new LinearLayoutManager(SchoolDetailActivity.this,
                    LinearLayoutManager.VERTICAL, false));
            teachersRvAdapter = new TeachersRvAdapter(this, teacherList);
            recyclerView1.setAdapter(teachersRvAdapter);
            recyclerView1.setItemAnimator(new DefaultItemAnimator());

            recyclerView2.setLayoutManager(new LinearLayoutManager(SchoolDetailActivity.this,
                    LinearLayoutManager.VERTICAL, false));
            studentsRvAdapter = new StudentsRvAdapter(this, studentInformations);
            recyclerView2.setAdapter(studentsRvAdapter);
            recyclerView2.setItemAnimator(new DefaultItemAnimator());
        } else {
            teachersRvAdapter.teachers = teacherList;
            teachersRvAdapter.notifyDataSetChanged();

            studentsRvAdapter.studentInformations = studentInformations;
            studentsRvAdapter.notifyDataSetChanged();
        }
    }

    public void deleteTeacher(Integer id) {
        statements.deleteTeacher(id);
        setupRv();
    }

    public void deleteStudent(Integer id) {
        statements.deleteStudent(id);
        setupRv();
    }
    public void back(View view) {
        Intent inten=new Intent(SchoolDetailActivity.this,SchoolsActivity.class);
        startActivity(inten);
    }
}
