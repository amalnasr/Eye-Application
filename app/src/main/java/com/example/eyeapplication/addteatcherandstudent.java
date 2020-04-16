package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class addteatcherandstudent extends AppCompatActivity {
    int schoolId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteatcherandstudent);

        if (getIntent().hasExtra("school_id"))
            schoolId = getIntent().getExtras().getInt("school_id");

        ImageView student = findViewById(R.id.imagestudent);
        ImageView teacher = findViewById(R.id.imageteacher);
        TextView TS = findViewById(R.id.tstudent);
        TextView TT = findViewById(R.id.tteacher);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddstudentInformation.class);
                intent.putExtra("school_id", schoolId);
                startActivity(intent);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddteacherInformation.class);
                intent.putExtra("school_id", schoolId);
                startActivity(intent);
            }
        });
        TT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddteacherInformation.class);
                intent.putExtra("school_id", schoolId);
                startActivity(intent);
            }
        });

        TS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddstudentInformation.class);
                intent.putExtra("school_id", schoolId);
                startActivity(intent);
            }
        });

    }

    public void back(View view) {
        finish();
    }

}

