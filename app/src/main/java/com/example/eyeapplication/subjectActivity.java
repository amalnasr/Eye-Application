package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eyeapplication.database.DatabaseStatements;

import java.util.List;

public class subjectActivity extends AppCompatActivity {

    int type;
    String studentNumber;
    StudentInformation studentInformation;
    List<SchoolLevel> levels;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        if (getIntent().hasExtra("type"))
            type = getIntent().getExtras().getInt("type");
        if (getIntent().hasExtra("studentNumber"))
            studentNumber = getIntent().getExtras().getString("studentNumber");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        studentInformation = databaseStatements.getStudentsByNumber(studentNumber);

        levels = databaseStatements.getSubjectsByLevel(studentInformation.getLev());

        list = (ListView) findViewById(R.id.list);
        ArrayAdapter adt = new ArrayAdapter<SchoolLevel>(this,
                android.R.layout.simple_list_item_1, levels);
        list.setAdapter(adt);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class activity = showHomeWork.class;
                if (type == 1)
                    activity = showRate.class;

                Intent intent = new Intent(subjectActivity.this, activity);
                intent.putExtra("studentNumber", studentNumber);
                intent.putExtra("subject", levels.get(position).getSubject());

                startActivity(intent);
            }
        });

    }

    public void back(View view) {
        finish();
    }
}
