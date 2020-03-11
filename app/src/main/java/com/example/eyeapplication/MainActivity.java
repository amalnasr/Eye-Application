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

import java.util.ArrayList;
import java.util.List;

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

        insertLevelsSubjects();

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

                if (P.length() < 5) {
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

    private void insertLevelsSubjects(){
        List<SchoolLevel> levels = new ArrayList<>();

        SchoolLevel level1 = new SchoolLevel(1,"الصف الأول الإبتدائي","الرياضيات");
        levels.add(level1);

        SchoolLevel level2 = new SchoolLevel(2,"الصف الأول الإبتدائي","العلوم");
        levels.add(level2);

        SchoolLevel level3 = new SchoolLevel(3,"الصف الأول الإبتدائي","القرآن الكريم");
        levels.add(level3);

        SchoolLevel level4 = new SchoolLevel(4,"الصف الأول الإبتدائي","اللغة العربية[لغتي]");
        levels.add(level4);

        SchoolLevel level5 = new SchoolLevel(5,"الصف الأول الإبتدائي","الفقه والسلوك");
        levels.add(level5);

        SchoolLevel level6 = new SchoolLevel(6,"الصف الأول الإبتدائي","التوحيد");
        levels.add(level6);

        SchoolLevel level7 = new SchoolLevel(7,"الصف الأول الإبتدائي","التربيةالفنية");
        levels.add(level7);

        SchoolLevel level8 = new SchoolLevel(8,"الصف الثاني الإبتدائي","الرياضيات");
        levels.add(level8);

        SchoolLevel level9 = new SchoolLevel(9,"الصف الثاني الإبتدائي","العلوم");
        levels.add(level9);

        SchoolLevel level10 = new SchoolLevel(10,"الصف الثاني الإبتدائي","القرآن الكريم");
        levels.add(level10);

        SchoolLevel level11 = new SchoolLevel(11,"الصف الثاني الإبتدائي","اللغة العربية[لغتي]");
        levels.add(level11);

        SchoolLevel level12 = new SchoolLevel(12,"الصف الثاني الإبتدائي","الفقه والسلوك");
        levels.add(level12);

        SchoolLevel level13 = new SchoolLevel(13,"الصف الثاني الإبتدائي","التوحيد");
        levels.add(level13);

        SchoolLevel level14 = new SchoolLevel(14,"الصف الثاني الإبتدائي","التربيةالفنية");
        levels.add(level14);

        SchoolLevel level15 = new SchoolLevel(15,"الصف الثالث الإبتدائي","الرياضيات");
        levels.add(level15);

        SchoolLevel level16 = new SchoolLevel(16,"الصف الثالث الإبتدائي","العلوم");
        levels.add(level16);

        SchoolLevel level17 = new SchoolLevel(17,"الصف الثالث الإبتدائي","القرآن الكريم");
        levels.add(level17);

        SchoolLevel level18 = new SchoolLevel(18,"الصف الثالث الإبتدائي","اللغة العربية[لغتي]");
        levels.add(level18);

        SchoolLevel level19 = new SchoolLevel(19,"الصف الثالث الإبتدائي","الفقه والسلوك");
        levels.add(level19);

        SchoolLevel level20 = new SchoolLevel(20,"الصف الثالث الإبتدائي","التوحيد");
        levels.add(level20);

        SchoolLevel level21 = new SchoolLevel(21,"الصف الثالث الإبتدائي","التربيةالفنية");
        levels.add(level21);

        DatabaseStatements statements = new DatabaseStatements(this);
        statements.newSchoolLevels(levels);
    }
}










