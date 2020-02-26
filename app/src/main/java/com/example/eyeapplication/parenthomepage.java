package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class parenthomepage extends AppCompatActivity {
    int userId = -1;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parenthomepage);

        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        user = databaseStatements.getUser(userId);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:

                        return true;


                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.add_student:
                        startActivity(new Intent(getApplicationContext(), AddstudentInformation.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });
    }
}
