package com.example.eyeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;


import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class editteacherinformation extends AppCompatActivity {


    int userId = -1;
    User user;
    Teacher teacher;
    DatabaseStatements databaseStatements;

    EditText name, username, pw, section,subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editteacherinformation);


        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        databaseStatements = new DatabaseStatements(this);
        user = databaseStatements.getUser(userId);
        teacher = databaseStatements.getTeacher(userId);

        name = findViewById(R.id.name);
        name.setText(teacher.getName());

        username = findViewById(R.id.username);
        username.setText(user.getEmail());

        section = findViewById(R.id.section);
        section.setText(teacher.getSections());

        subject = findViewById(R.id.subject);
        subject.setText(teacher.getSubject());

        pw = findViewById(R.id.pw);
        pw.setText(user.getPw());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.account:

                        return true;


                    case R.id.home:
                        Intent intent = new Intent (editteacherinformation.this,teacherhomepage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("userId",userId);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }

        });
    }

    public void back(View view) {
        Intent inten = new Intent( editteacherinformation.this, MainActivity.class);
        startActivity(inten);
    }

    public void update(View view) {
        if (TextUtils.isEmpty(name.getText().toString().trim())) {
            Toast.makeText(editteacherinformation.this, "من فضلك ادخل الأسم", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pw.getText().toString().trim())) {
            Toast.makeText(editteacherinformation.this, "من فضلك ادخل كلمه المرور", Toast.LENGTH_LONG).show();
            return;
        }

        if (pw.getText().toString().trim().length() < 6) {
            Toast.makeText(editteacherinformation.this, "كلمه المرور يجب ان تكون علي الاقل 6 أخرف أو أرقام", Toast.LENGTH_LONG).show();
            return;
        }

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setType(user.getType());
        updateUser.setEmail(user.getEmail());
        updateUser.setName(name.getText().toString().trim());
        updateUser.setPw(pw.getText().toString().trim());

        databaseStatements.updateUser(updateUser);

        teacher.setName(user.getName());

        databaseStatements.updateTeacher(teacher);

        Toast.makeText(editteacherinformation.this, "تم حفظ التعديل بنجاح", Toast.LENGTH_LONG).show();

    }
}
