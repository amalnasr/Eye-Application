package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class submitDuties extends AppCompatActivity {
    Button sen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_duties);
        sen=(Button)findViewById(R.id.button7);
    }
    public void send(View view) {
        Intent in=new Intent(this,submitDuties.class);
        startActivity(in);
    }
}
