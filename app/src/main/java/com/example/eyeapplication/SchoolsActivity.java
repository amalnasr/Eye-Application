package com.example.eyeapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyeapplication.database.DatabaseStatements;

import java.util.List;

public class SchoolsActivity extends AppCompatActivity {
    AlertDialog A;
    AlertDialog.Builder B;
    RecyclerView recyclerView;
    SchoolsRvAdapter schoolsRvAdapter;
    List<School> schools;
    DatabaseStatements statements;
    int ID ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools);

        statements = new DatabaseStatements(this);
        recyclerView = findViewById(R.id.rv);

        setupRv();

        B = new AlertDialog.Builder(SchoolsActivity.this);
        B.setTitle( "هل ترغب في حذف المدرسة؟ مع العلم أنه سيتم حذف جميع الطلاب والمعلمين المضافين فيها!");

        B.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                statements.deleteSchool(ID);
                setupRv();
            }
        });
        B.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        A = B.create();

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

    public void deleteSchool( Integer id) {
        ID =id;
        A.show();



    }

    public void goToDetails(Integer id) {
        Intent intent = new Intent(SchoolsActivity.this,SchoolDetailActivity.class);
        intent.putExtra("schoolId",id);
        startActivity(intent);
    }
}
