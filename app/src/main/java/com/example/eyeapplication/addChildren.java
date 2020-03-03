package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addChildren extends AppCompatActivity {
    Button addc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_children);
        addc = (Button) findViewById(R.id.button4);

    }

    public void ms(View view) {
        Intent y = new Intent(this,viewChildren.class);
        startActivity(y);

    }
}
