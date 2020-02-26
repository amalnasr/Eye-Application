package com.example.eyeapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;

import static com.example.eyeapplication.database.DatabaseHelper.PARENT;

//import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText email, Password;
    Button Login;
    TextView T, T2;
    String pass, name, nameT, passT;
    String E, P;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        T2 = (TextView) findViewById(R.id.appname);
        T2.setTypeface(Typeface.createFromAsset(getAssets(), "DTPN3.ttf"));

        email = (EditText) findViewById(R.id.eusername);
        Password = (EditText) findViewById(R.id.epassword);
        Login = (Button) findViewById(R.id.elogin);
        T = (TextView) findViewById(R.id.textView4);

        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registration.class));

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                E = email.getText().toString().trim();
                P = Password.getText().toString().trim();


                if (TextUtils.isEmpty(E)) {
                    email.setError("البريد الإلكتروني مطلوب!");
                    return;
                }

                if (TextUtils.isEmpty(P)) {
                    Password.setError("كلمة المرور مطلوبة!");
                    return;
                }

                if (P.length() < 6) {
                    Password.setError("ينبغي أن تكون كلمة المرور مكونة من 6 أحرف على الأقل!");
                    return;
                }

                //Admin Login
                if ((E.equals("admin@admin.com")) && (P.equals("admin2020"))) {
                    Intent i = new Intent(MainActivity.this, Addscchool.class);
                    startActivity(i);
                    finish();
                    return;
                } else {
                    DatabaseStatements statements = new DatabaseStatements(MainActivity.this);
                    User user = statements.userLogin(E, P);

                    if (user == null) {
                        Toast.makeText(MainActivity.this, "فشل تسجيل الدخول", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_LONG).show();

                        Class activity = teacherhomepage.class;

                        if (user.getType() == PARENT)
                            activity = parenthomepage.class;

                        Intent intent = new Intent(MainActivity.this, activity);
                        intent.putExtra("userId",user.getId());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}










