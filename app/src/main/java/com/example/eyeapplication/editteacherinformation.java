package com.example.eyeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class editteacherinformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editteacherinformation);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.account:

                        return true;


                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), teacherhomepage.class));
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
}
