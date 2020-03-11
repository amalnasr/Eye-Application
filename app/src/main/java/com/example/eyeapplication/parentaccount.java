package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class parentaccount extends AppCompatActivity {
    //  Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentaccount);
        // toolbar=(Toolbar)findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        // getSupportActionBar().setTitle("حسابي");


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), parenthomepage.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.account:
                        return true;

                    case R.id.add_student:
                        startActivity(new Intent(getApplicationContext(), addChildren.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });
    }


  /*  public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.toolmenue,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

       switch (item.getItemId()){
           case R.id.logout:
               startActivity(new Intent(parentaccount.this,MainActivity.class));
               Toast.makeText(this,"تم تسجيل الخروج بنجاح",Toast.LENGTH_SHORT).show();;

       }
        return super.onOptionsItemSelected(item);


    }*/
}
