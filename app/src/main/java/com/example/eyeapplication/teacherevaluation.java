package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;

public class teacherevaluation extends AppCompatActivity {

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;

    String score1, score2, score3, score4;

    int teacherId, studentId;
    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherevaluation);

        if (getIntent().hasExtra("tId"))
            teacherId = getIntent().getExtras().getInt("tId");

        if (getIntent().hasExtra("sId"))
            studentId = getIntent().getExtras().getInt("sId");

        if (getIntent().hasExtra("subject"))
            subject = getIntent().getExtras().getString("subject");

        radioGroup1 = findViewById(R.id.radiogroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        score1 = "2";
                        break;
                    case R.id.radioButton2:
                        score1 = "1";
                        break;
                    case R.id.radioButton3:
                        score1 = "0";
                        break;
                }
            }
        });

        radioGroup2 = findViewById(R.id.radiogroup2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton4:
                        score2 = "2";
                        break;
                    case R.id.radioButton5:
                        score2 = "1";
                        break;
                    case R.id.radioButton6:
                        score2 = "0";
                        break;
                }
            }
        });

        radioGroup3 = findViewById(R.id.radiogroup3);
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton7:
                        score3 = "2";
                        break;
                    case R.id.radioButton8:
                        score3 = "1";
                        break;
                    case R.id.radioButton9:
                        score3 = "0";
                        break;
                }
            }
        });

        radioGroup4 = findViewById(R.id.radiogroup4);
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton10:
                        score4 = "2";
                        break;
                    case R.id.radioButton11:
                        score4 = "1";
                        break;
                    case R.id.radioButton12:
                        score4 = "0";
                        break;
                }
            }
        });
    }

    public void done(View view) {
        if (score1 == null && score2 == null && score3 == null && score4 == null) {
            Toast.makeText(teacherevaluation.this, "من فضلك قيم مستوي واحد علي الأقل", Toast.LENGTH_LONG).show();
            return;
        }

        Rate rate = new Rate(teacherId, subject, studentId, score1, score2, score3, score4);
        DatabaseStatements statements = new DatabaseStatements(teacherevaluation.this);
        statements.newRate(rate);

        Toast.makeText(teacherevaluation.this, "تم حفظ التقييم بنجاح", Toast.LENGTH_LONG).show();

        finish();
    }

    public void back(View view) {
        Intent inten = new Intent( teacherevaluation.this, teacherhomepage.class);
        startActivity(inten);
    }
}
