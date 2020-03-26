package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;
import com.example.eyeapplication.notification.Notification;
import com.example.eyeapplication.notification.ShowNotification;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class parenthomepage extends AppCompatActivity {
    int userId = -1;
    User user;
    ListView list;
    List<StudentInformation> studentInformationList;
    DatabaseStatements databaseStatements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parenthomepage);

        if (getIntent().hasExtra("userId"))
            userId = getIntent().getExtras().getInt("userId");

        databaseStatements = new DatabaseStatements(this);
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
                        Intent intent1 = new Intent(getApplicationContext(), editparentinformation.class);
                        intent1.putExtra("userId", userId);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.add_student:
                        Intent intent = new Intent(getApplicationContext(), addChildren.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentInformationList = databaseStatements.getStudentsByMotherId(user.getIdentityId());

        for (int s=0; s<studentInformationList.size(); s++) {
            StudentInformation studentInformation = studentInformationList.get(s);

            List<Notification> notifications = databaseStatements.getNotifications(studentInformation.getSchoolId());

            for (int n = 0; n < notifications.size(); n++) {
                Notification notification = notifications.get(n);
                if (notification.getStudentId() == 0 ||
                        studentInformation.getId() == (int) notification.getStudentId()){

                    String title = notification.getTitle() + " للطالب "+ studentInformation.getName();

                    ShowNotification.buildNotification(parenthomepage.this,"لديك إشعار جديد",title);

                    databaseStatements.updateNotificationStatus(notification);
                }
            }
        }

        list = (ListView) findViewById(R.id.list);
        ArrayAdapter adt = new ArrayAdapter<StudentInformation>(this,
                android.R.layout.simple_list_item_1, studentInformationList);
        list.setAdapter(adt);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent y = new Intent(parenthomepage.this, viewChildrenpage.class);
                y.putExtra("studentNumber", studentInformationList.get(position).getStudentid());
                startActivity(y);
            }
        });
    }
}
