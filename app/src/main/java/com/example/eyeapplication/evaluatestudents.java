package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eyeapplication.database.DatabaseHelper;
import com.example.eyeapplication.database.DatabaseStatements;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class evaluatestudents extends AppCompatActivity {

    ListView list;
    DatabaseHelper mdb;
    SQLiteDatabase db;
    DatabaseStatements dbs;
    int schoolId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluatestudents);



//and mdb.schoolId=teacher.schoolId and mdb.schoolId=student.schoolId

        list =(ListView)findViewById(R.id.listS);
       // dbs.openDatabase();
        mdb = new DatabaseHelper(evaluatestudents.this);
        db= mdb.getReadableDatabase();
        Cursor C = db.rawQuery("select student.name from student,teacher WHERE student.section= teacher.section",null);
        C.moveToFirst();
        ArrayList<String> List = new ArrayList<String>();
        while(C.isAfterLast()==false){

           List.add(C.getString(0));
           C.moveToNext();
        }
        ArrayAdapter adt = new ArrayAdapter(this,android.R.layout.simple_list_item_1,List);
        list.setAdapter(adt);

     //   C.close();
      //  dbs.closeDatabase();
    }

}
