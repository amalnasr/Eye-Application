package com.example.eyeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class addChildren extends AppCompatActivity {
    int userId = -1;
    User user;

    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_children);

        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        user = databaseStatements.getUser(userId);
        input = findViewById(R.id.editText6);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.add_student);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(), parenthomepage.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.add_student:
                        return true;
                }
                return false;
            }

        });

    }

    public void ms(View view) {
        if (input.getText().toString().trim().length() != 5){
            Toast.makeText(addChildren.this,"ادخل رقم الطالب مكون من 5 خانات",Toast.LENGTH_LONG).show();
            return;
        }

        DatabaseStatements databaseStatements = new DatabaseStatements(addChildren.this);
        StudentInformation studentInformation  = databaseStatements.getStudentsByNumber(input.getText().toString().trim());

        if (studentInformation != null) {
            if (!user.getIdentityId().equals(studentInformation.getMid())) {
                Toast.makeText(addChildren.this, "رقم هويه ولي الأمر المسجل لدي هذا الطالب ليس رقم هويتك . نأسف لا يمكننا عرض بياناته", Toast.LENGTH_LONG).show();
                return;
            } else {
                databaseStatements.updateStudentStatus(studentInformation);
                Toast.makeText(addChildren.this, "تم اضافه الطالب بنجاح", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), parenthomepage.class));
            }
        }else {
            Toast.makeText(addChildren.this, "لا يوجد طالب مسجل بهذا الرقم", Toast.LENGTH_LONG).show();
            return;
        }
    }
}
