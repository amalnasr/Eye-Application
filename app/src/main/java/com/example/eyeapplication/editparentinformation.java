package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class editparentinformation extends AppCompatActivity {

    int userId = -1;
    User user;
    DatabaseStatements databaseStatements;

    EditText name, id, email, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editparentinformation);

        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        databaseStatements = new DatabaseStatements(this);
        user = databaseStatements.getUser(userId);

        name = findViewById(R.id.name);
        name.setText(user.getName());

        id = findViewById(R.id.identityId);
        id.setText(user.getIdentityId());

        email = findViewById(R.id.email);
        email.setText(user.getEmail());

        pw = findViewById(R.id.pw);
        pw.setText(user.getPw());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent (editparentinformation.this,parenthomepage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("userId",userId);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.account:

                        return true;

                    case R.id.add_student:
                        Intent intent2 = new Intent(getApplicationContext(), addChildren.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });
    }

    public void back(View view) {
        finish();
    }

    public void update(View view) {
        if (TextUtils.isEmpty(name.getText().toString().trim())) {
            Toast.makeText(editparentinformation.this, "من فضلك ادخل الأسم", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pw.getText().toString().trim())) {
            Toast.makeText(editparentinformation.this, "من فضلك ادخل كلمه المرور", Toast.LENGTH_LONG).show();
            return;
        }

        if (pw.getText().toString().trim().length() < 6) {
            Toast.makeText(editparentinformation.this, "كلمه المرور يجب ان تكون علي الاقل 6 حروف", Toast.LENGTH_LONG).show();
            return;
        }

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setType(user.getType());
        updateUser.setEmail(user.getEmail());
        updateUser.setName(name.getText().toString().trim());
        updateUser.setPw(pw.getText().toString().trim());

        databaseStatements.updateUser(updateUser);

        Toast.makeText(editparentinformation.this, "تم تعديل الحساب بنجاح", Toast.LENGTH_LONG).show();

    }
}
