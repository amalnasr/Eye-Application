package com.example.eyeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeapplication.database.DatabaseStatements;
import com.example.eyeapplication.database.User;

import static com.example.eyeapplication.database.DatabaseHelper.PARENT;

public class Registration extends AppCompatActivity {
    EditText NameP, ID, emailP, passP;
    Button B;
    ImageView R1 ;
    // Member member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        R1 = findViewById(R.id.imageViewR);
        B = findViewById(R.id.button2);
        NameP = findViewById(R.id.editText);
        emailP = findViewById(R.id.editText4);
        ID = findViewById(R.id.editText3);
        passP = findViewById(R.id.editText9);


        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String E = emailP.getText().toString().trim();
                String password = passP.getText().toString().trim();
                final String name = NameP.getText().toString().trim();
                final String idP = ID.getText().toString().trim();

                if (TextUtils.isEmpty(E)) {
                    emailP.setError("البريد الإلكتروني مطلوب!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passP.setError("كلمة المرور مطلوبة!");
                    return;
                }

                if (password.length() < 6) {
                    passP.setError("ينبغي أن تكون كلمة المرور مكونة من 6 أحرف ع الأقل!");
                    return;
                }
                if ((idP.length() < 10) && (idP.length() > 10)) {

                    ID.setError("ينبغي أن يكون رقم الهوية مكون من 10 أرقام فقط!");
                    return;
                }

                DatabaseStatements databaseStatements = new DatabaseStatements(Registration.this);

                User user = new User(name, E, idP, password, PARENT);

                boolean exist = databaseStatements.userValidation(user.getEmail());

                if (exist) {
                    Toast.makeText(Registration.this, "هذا البريد مستخدم بالفعل"
                            , Toast.LENGTH_LONG).show();
                } else {
                    boolean exist2 = databaseStatements.userIdValidation(user.getIdentityId());
                    if (exist2) {
                        Toast.makeText(Registration.this, "رقم الهويه مستخدم بالفعل"
                                , Toast.LENGTH_LONG).show();
                    } else {
                        user = databaseStatements.newUser(user);

                        Toast.makeText(Registration.this,
                                "تم التسجيل بنجاح", Toast.LENGTH_LONG).show();
                        open();
                    }
                }

//                fAuth.createUserWithEmailAndPassword(E,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(Registration.this, "تم إنشاء الحساب بنجاح", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Toast.makeText(Registration.this, "خطأ حاول مره أخرى!" , Toast.LENGTH_SHORT).show();  //+ task.getException().getMessage()
//                            // progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                });
            }
        });

    }


    public void open() {
        startActivity(new Intent(Registration.this, MainActivity.class));
        finish();
    }

    public void open1(View view) {
        startActivity(new Intent(Registration.this, Registration.class));

    }

    public void back(View view) {
        Intent inten = new Intent( Registration.this, MainActivity.class);
        startActivity(inten);
    }
}