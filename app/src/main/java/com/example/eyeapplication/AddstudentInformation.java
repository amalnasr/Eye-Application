package com.example.eyeapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;

import java.util.ArrayList;

public class AddstudentInformation extends AppCompatActivity {
    EditText name, motherid, fatherid, studentid, level, section;
    Button add;
    StudentInformation student;
    ImageView R1 ;
    String[] listItems, arr;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    int schoolId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent_information);



        if (getIntent().hasExtra("school_id"))
            schoolId = getIntent().getExtras().getInt("school_id");
        R1 = findViewById(R.id.imageViewS);
        listItems = getResources().getStringArray(R.array.class_item);
        checkedItems = new boolean[listItems.length];
        name = (EditText) findViewById(R.id.editText);
        motherid = (EditText) findViewById(R.id.editText2);
        fatherid = (EditText) findViewById(R.id.editText4);
        studentid = (EditText) findViewById(R.id.editText5);
        level = (EditText) findViewById(R.id.editText6);
        section = (EditText) findViewById(R.id.editText7);
        add = (Button) findViewById(R.id.button2);
        student = new StudentInformation();
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddstudentInformation.this, addteatcherandstudent.class));
            }
        });
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddstudentInformation.this);
                mBuilder.setTitle(R.string.dialog_title2);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            mUserItems.add(position);
                        } else {
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("تم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + "،";
                            }

                        }
                        level.setText(item);
                    }
                });

                mBuilder.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = name.getText().toString().trim();
                String mi = motherid.getText().toString().trim();
                String fi = fatherid.getText().toString().trim();
                String Id = studentid.getText().toString().trim();
                String le = level.getText().toString().trim();
                String se = section.getText().toString().trim();
                student.setName(nam);
                student.setMid(mi);
                student.setFid(fi);
                student.setStudentid(Id);
                student.setLev(le);
                student.setSec(se);
                student.setSchoolId(schoolId);

                DatabaseStatements databaseStatements = new DatabaseStatements(AddstudentInformation.this);
                databaseStatements.newStudent(student);

                Toast.makeText(AddstudentInformation.this, "تم إضافة الطالب بنجاح!", Toast.LENGTH_LONG).show();
                AddstudentInformation.this.finish();
            }
        });
    }
}
