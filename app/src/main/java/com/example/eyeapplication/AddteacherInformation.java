package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;

import static com.example.eyeapplication.database.DatabaseHelper.TEACHER;


public class AddteacherInformation extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    EditText nameT, userT, passT;
    Button addTeacher,delete;
    Spinner subjectT , sectionT;
    //Spinner myspinner;
    Teacher teacher;
    //ValueEventListener listener;

    //String[] listItems, arr;
    //boolean[] checkedItems;
    //ArrayList<Integer> mUserItems = new ArrayList<>();

    int schoolId = 0;

    //ArrayAdapter<String> adapter;
    //ArrayList<String> spinnerDataLis
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteacher_information);

        if (getIntent().hasExtra("school_id"))
            schoolId = getIntent().getExtras().getInt("school_id");

        nameT = (EditText) findViewById(R.id.nameT);
        subjectT = (Spinner) findViewById(R.id.spinner2t);
        sectionT = (Spinner) findViewById(R.id.spinner1t);
        //myspinner=(Spinner) findViewById(R.id.myspinner);
        userT = (EditText) findViewById(R.id.usernameT);
        passT = (EditText) findViewById(R.id.passT);
        addTeacher = (Button) findViewById(R.id.Tadd);
        delete = (Button) findViewById(R.id.button3);

     /*   listItems = getResources().getStringArray(R.array.subject_item);
        checkedItems = new boolean[listItems.length];
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddteacherInformation.this, addteatcherandstudent.class));
            }
        });

        subjectT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddteacherInformation.this);
                mBuilder.setTitle(R.string.dialog_title);
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
                        subjectT.setText(item);
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


        /*reff2= FirebaseDatabase.getInstance().getReference("spinnerData");
        spinnerDataList=new ArrayList<>();
        adapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item.spinnerDataList);
        myspinner.setAdapter(adapter);

        retrieveData();*/

        // teacher.setName(myspinner.get  .toString().trim());

        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String N = nameT.getText().toString().trim();
                String U = userT.getText().toString().trim();
                String p = passT.getText().toString().trim();
                String S = sectionT.getSelectedItem().toString().trim();
                String S2 = subjectT.getSelectedItem().toString().trim();

              /*  teacher.setSections(S);
                teacher.setUsername(U);
                teacher.setName(N);
                teacher.setPass(p);
                teacher.setSubject(S2);*/
                teacher = new Teacher(N, S2, S, U, p);

                DatabaseStatements databaseStatements = new DatabaseStatements(AddteacherInformation.this);
                User user = new User(N, U, null, p, TEACHER);

                boolean exist = databaseStatements.userValidation(user.getEmail());
                if (exist) {
                    Toast.makeText(AddteacherInformation.this, "هذا البريد مستخدم بالفعل"
                            , Toast.LENGTH_LONG).show();
                } else {
                    user = databaseStatements.newUser(user);
                    teacher.setUserId(user.getId());
                    teacher.setSchoolId(schoolId);

                    databaseStatements.newTeacher(teacher);

                    Toast.makeText(AddteacherInformation.this, "تم إضافة المعلم بنجاح!", Toast.LENGTH_LONG).show();
                    AddteacherInformation.this.finish();
                }
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?>_adapterView, View view,
                               int pos, long id) {
        //Toast.makeText(this,_adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }
    @Override
    public void onNothingSelected(AdapterView<?> _adapterView) {
        // Another interface callback
    }
    public void ret(View view) {
        Intent inten=new Intent(AddteacherInformation.this,addteatcherandstudent.class);
        startActivity(inten);
    }
    public void back(View view) {
        Intent inten=new Intent(AddteacherInformation.this,addteatcherandstudent.class);
        startActivity(inten);
    }

}


