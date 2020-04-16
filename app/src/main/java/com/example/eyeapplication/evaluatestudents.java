package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eyeapplication.database.DatabaseHelper;
import com.example.eyeapplication.database.DatabaseStatements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class evaluatestudents extends AppCompatActivity {

    ListView list;

    int userId = -1;
    Teacher teacher;
    List<StudentInformation> studentInformationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluatestudents);

        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        teacher = databaseStatements.getTeacher(userId);

        studentInformationList = databaseStatements.getStudentsBySection(teacher.getSections());

        list =(ListView)findViewById(R.id.listS);
        ArrayAdapter adt = new ArrayAdapter<StudentInformation>(this,
                android.R.layout.simple_list_item_1,studentInformationList);
        list.setAdapter(adt);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(evaluatestudents.this,teacherevaluation.class);
                intent.putExtra("tId",teacher.getId());
                intent.putExtra("sId",studentInformationList.get(position).getId());
                intent.putExtra("subject",teacher.getSubject());
                startActivity(intent);
            }
        });
    }

    public void back(View view) {
        finish();
    }
}
