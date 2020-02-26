package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.widget.Toast.LENGTH_SHORT;

public class Addscchool extends AppCompatActivity {
    int count = 1;
    Button adds;
    EditText addschool;
    School school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addscchool);
        addschool = (EditText) findViewById(R.id.eaddschool);
        adds = (Button) findViewById(R.id.badd);
        school = new School();

        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(addschool.getText().toString().trim())) {
                    Toast.makeText(Addscchool.this, "من فضلك ادخل اسم المدرسه", LENGTH_SHORT).show();
                    return;
                }

                school.setName(addschool.getText().toString().trim());

                DatabaseStatements databaseStatements = new DatabaseStatements(Addscchool.this);
                school = databaseStatements.newSchool(school);

                count = count + 1;

                Toast.makeText(Addscchool.this, "تم إضافة المدرسة بنجاح", LENGTH_SHORT).show();
                Intent i = new Intent(Addscchool.this, addteatcherandstudent.class);
                i.putExtra("school_id", school.getId());
                startActivity(i);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //bottomNavigationView.setSelectedItemId(R.id.add_school);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.add_school:

                        return true;


                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), SchoolsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.signout:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });
    }

}


