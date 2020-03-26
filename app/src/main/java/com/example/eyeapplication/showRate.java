package com.example.eyeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eyeapplication.database.DatabaseStatements;

public class showRate extends AppCompatActivity {

    String studentNumber,subject;
    StudentInformation studentInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rate);

        if (getIntent().hasExtra("studentNumber"))
            studentNumber = getIntent().getExtras().getString("studentNumber");

        if (getIntent().hasExtra("subject"))
            subject = getIntent().getExtras().getString("subject");

        DatabaseStatements databaseStatements = new DatabaseStatements(this);
        studentInformation = databaseStatements.getStudentsByNumber(studentNumber);

        Rate rate = databaseStatements.getSubjectRateForStudent(subject,studentInformation.getId());

        if (rate != null){
            TextView textView = findViewById(R.id.title);
            String total = "";
            String read = "تقييم قراءة النصوص ";
            String write = "تقييم الإملاء اليومي ";
            String save = "تقييم حفظ النصوص ";
            String homeWork = " تقييم اداء الواجب ";

            if (rate.getReadCat() != null) {
                switch (rate.getReadCat()) {
                    case "2":
                        read = read.concat(" : ").concat("اتقن بجداره");
                        break;
                    case "1":
                        read = read.concat(" : ").concat("اتقن");
                        break;
                    case "0":
                        read = read.concat(" : ").concat("لم يتقن");
                        break;
                }

                total = total.concat(read);
            }

            if (rate.getWriteCat() != null) {
                switch (rate.getWriteCat()) {
                    case "2":
                        write = write.concat(" : ").concat("اتقن بجداره");
                        break;
                    case "1":
                        write = write.concat(" : ").concat("اتقن");
                        break;
                    case "0":
                        write = write.concat(" : ").concat("لم يتقن");
                        break;
                }
                total = total.concat("\n").concat(write);
            }
            if (rate.getSaveCat() != null) {
                switch (rate.getSaveCat()) {
                    case "2":
                        save = save.concat(" : ").concat("تم الحفظ");
                        break;
                    case "1":
                        save = save.concat(" : ").concat("لم يتم");
                        break;
                    case "0":
                        save = save.concat(" : ").concat("ارجو المتابعه");
                        break;
                }
                total = total.concat("\n").concat(save);
            }
            if (rate.getHomeworkCat() != null) {
                switch (rate.getHomeworkCat()) {
                    case "2":
                        homeWork = homeWork.concat(" : ").concat("تم اداء الواجب");
                        break;
                    case "1":
                        homeWork = homeWork.concat(" : ").concat("لم يتم");
                        break;
                    case "0":
                        homeWork = homeWork.concat(" : ").concat("ارجو المتابعه");
                        break;
                }
                total = total.concat("\n").concat(homeWork);
            }
            textView.setText(total);
        }
    }
    public void back(View view) {
        finish();
    }
}
