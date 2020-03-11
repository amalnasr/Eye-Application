package com.example.eyeapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int ADMIN = 0;
    public static final int TEACHER = 1;
    public static final int PARENT = 2;

    public static final String id = "id";
    public static final String name = "name";
    public static final String email = "email";
    public static final String password = "password";
    public static final String identityId = "identityId";
    public static final String type = "type";
    public static final String motherId = "mother_id";
    public static final String fatherId = "father_id";
    public static final String level = "level";
    public static final String section = "section";
    public static final String schoolId = "school_id";
    public static final String subject = "subject";
    public static final String teacherId = "teacher_id";
    public static final String homeworkId = "homework_id";
    public static final String studentId = "student_id";
    public static final String answer = "answer";
    public static final String readCat = "read_category";
    public static final String writeCat = "write_category";
    public static final String saveCat = "save_category";
    public static final String homeWorkCat = "homework_category";
    public static final String studentNumber = "student_number";
    public static final String studentStatus = "student_status";

    public static final String user = "user";
    public static final String school = "school";
    public static final String student = "student";
    public static final String teacher = "teacher";
    public static final String homework = "homework";
    public static final String homeworkAnswer = "homework_answer";
    public static final String rating = "rating";
    public static final String subjects = "subjects";


    String user_statement = "CREATE TABLE " + user + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + name + " TEXT NOT NULL,"
            + email + " TEXT NOT NULL UNIQUE,"
            + identityId + " TEXT NULL UNIQUE,"
            + password + " TEXT NOT NULL ,"
            + type + " INTEGER );";

    String school_statement = "CREATE TABLE " + school + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + name + " TEXT NOT NULL );";

    String student_statement = "CREATE TABLE " + student + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + name + " TEXT NOT NULL ,"
            + motherId + " TEXT NOT NULL UNIQUE,"
            + fatherId + " TEXT NOT NULL UNIQUE ,"
            + level + " TEXT NOT NULL,"
            + section + " TEXT NOT NULL,"
            + studentNumber + " TEXT NOT NULL,"
            + studentStatus + " INTEGER NOT NULL,"
            + schoolId + " INTEGER );";

    String subjects_statement = "CREATE TABLE " + subjects + " ("
            + id + " INTEGER PRIMARY KEY , "
            + level + " TEXT NOT NULL ,"
            + subject + " TEXT NOT NULL );";

    String teacher_statement = "CREATE TABLE " + teacher + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + teacherId + " INTEGER ,"
            + name + " TEXT NOT NULL,"
            + subject + " TEXT NOT NULL,"
            + section + " TEXT NOT NULL,"
            + schoolId + " INTEGER );";

    String homework_statement = "CREATE TABLE " + homework + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + teacherId + " INTEGER ,"
            + name + " TEXT NOT NULL,"
            + section + " TEXT NOT NULL,"
            + subject + " TEXT NOT NULL,"
            + schoolId + " INTEGER );";

    String homework_answer_statement = "CREATE TABLE " + homeworkAnswer + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + homeworkId + " INTEGER ,"
            + teacherId + " INTEGER ,"
            + studentId + " INTEGER ,"
            + answer + " TEXT NOT NULL,"
            + section + " TEXT NOT NULL,"
            + schoolId + " INTEGER );";

    String rating_statement = "CREATE TABLE " + rating + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + teacherId + " INTEGER ,"
            + subject + " TEXT ,"
            + studentId + " INTEGER ,"
            + readCat + " TEXT ,"
            + writeCat + " TEXT,"
            + saveCat + " TEXT,"
            + homeWorkCat + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, "school.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(user_statement);
        sqLiteDatabase.execSQL(school_statement);
        sqLiteDatabase.execSQL(student_statement);
        sqLiteDatabase.execSQL(teacher_statement);
        sqLiteDatabase.execSQL(homework_statement);
        sqLiteDatabase.execSQL(homework_answer_statement);
        sqLiteDatabase.execSQL(rating_statement);
        sqLiteDatabase.execSQL(subjects_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + user_statement);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + school_statement);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + student_statement);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + teacher_statement);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + homework_statement);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + homework_answer_statement);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + rating_statement);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + subjects_statement);
    }
}
