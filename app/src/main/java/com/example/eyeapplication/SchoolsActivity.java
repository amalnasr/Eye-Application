package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyeapplication.database.DatabaseStatements;

import java.util.List;

public class SchoolsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SchoolsRvAdapter schoolsRvAdapter;
    List<School> schools;
    DatabaseStatements statements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools);

        statements = new DatabaseStatements(this);
        recyclerView = findViewById(R.id.rv);

        setupRv();
    }

    private void setupRv() {
        schools = statements.getSchools();

        if (schoolsRvAdapter == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(SchoolsActivity.this,
                    LinearLayoutManager.VERTICAL, false));
            schoolsRvAdapter = new SchoolsRvAdapter(this, schools);
            recyclerView.setAdapter(schoolsRvAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } else {
            schoolsRvAdapter.schools = schools;
            schoolsRvAdapter.notifyDataSetChanged();
        }
    }

    public void deleteSchool(Integer id) {
        statements.deleteSchool(id);
        setupRv();
    }

    public void goToDetails(Integer id) {
        Intent intent = new Intent(SchoolsActivity.this,SchoolDetailActivity.class);
        intent.putExtra("schoolId",id);
        startActivity(intent);
    }
}
