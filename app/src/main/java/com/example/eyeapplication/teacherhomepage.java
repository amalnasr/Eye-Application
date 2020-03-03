package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class teacherhomepage extends AppCompatActivity {

    ImageView V ;
    TextView T;
    int userId = -1;
    Teacher teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherhomepage);
             V = findViewById(R.id.imageView5);
             T = findViewById(R.id.textView);

             V.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     startActivity(new Intent (teacherhomepage.this,evaluatestudents.class));
                 }
             });
        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (teacherhomepage.this,evaluatestudents.class));
            }
        });
        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        teacher = databaseStatements.getTeacher(userId);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //bottomNavigationView.setSelectedItemId(R.id.add_school);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.account:

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.home:
                        // startActivity(new Intent(getApplicationContext(), AdminHome.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }

        });
    }

    public void tyr(View view) {
        Intent im=new Intent(this,submitDuties.class);
        startActivity(im);
    }
}
