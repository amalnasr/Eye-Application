package com.example.eyeapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.eyeapplication.Homework;
import com.example.eyeapplication.HomeworkAnswer;
import com.example.eyeapplication.Rate;
import com.example.eyeapplication.School;
import com.example.eyeapplication.SchoolLevel;
import com.example.eyeapplication.StudentInformation;
import com.example.eyeapplication.Teacher;

import java.util.ArrayList;
import java.util.List;

public class DatabaseStatements {

    private DatabaseHelper sqlLiteHandler;
    private SQLiteDatabase sqLiteDatabase;

    private Context mContext;

    public DatabaseStatements(Context context) {
        sqlLiteHandler = new DatabaseHelper(context);
        this.mContext = context;
    }


    private void openDatabase() throws SQLException {
        sqLiteDatabase = sqlLiteHandler.getWritableDatabase();
    }

    private void closeDatabase() {
        sqLiteDatabase.close();
        sqlLiteHandler.close();
    }

    public boolean userValidation(String email) {
        openDatabase();
        boolean found = false;

        String statement = "SELECT " + DatabaseHelper.id + " FROM " + DatabaseHelper.user
                + " WHERE " + DatabaseHelper.email + " = '" + email + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            found = true;
        }

        cursor.close();
        closeDatabase();
        return found;
    }

    public boolean userIdValidation(String id) {
        openDatabase();
        boolean found = false;

        String statement = "SELECT " + DatabaseHelper.id + " FROM " + DatabaseHelper.user
                + " WHERE " + DatabaseHelper.identityId + " = '" + id + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            found = true;
        }

        cursor.close();
        closeDatabase();
        return found;
    }

    public User userLogin(String email, String password) {
        openDatabase();
        User user = null;

        String statement = "SELECT * FROM " + DatabaseHelper.user
                + " WHERE " + DatabaseHelper.email + " = '" + email + "'"
                + " AND " + DatabaseHelper.password + " = '" + password + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setIdentityId(cursor.getString(3));
            user.setPw(cursor.getString(4));
            user.setType(cursor.getInt(5));
        }

        cursor.close();
        closeDatabase();
        return user;
    }

    public void newSchoolLevels(List<SchoolLevel> schoolLevels) {
        openDatabase();
        for (SchoolLevel schoolLevel : schoolLevels) {
            ContentValues cv = new ContentValues();

            cv.put(DatabaseHelper.id, schoolLevel.getId());
            cv.put(DatabaseHelper.level, schoolLevel.getLevel());
            cv.put(DatabaseHelper.subject, schoolLevel.getSubject());

            sqLiteDatabase.insert(DatabaseHelper.subjects, null, cv);
        }
        closeDatabase();
    }

    public List<SchoolLevel> getSubjectsByLevel(String level) {
        openDatabase();
        List<SchoolLevel> levels = new ArrayList<>();

        String statement = "SELECT * FROM " + DatabaseHelper.subjects
                + " WHERE " + DatabaseHelper.level + " = '" + level + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                SchoolLevel schoolLevel = new SchoolLevel();

                schoolLevel.setId(cursor.getInt(0));
                schoolLevel.setLevel(cursor.getString(1));
                schoolLevel.setSubject(cursor.getString(2));

                levels.add(schoolLevel);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return levels;
    }

    public User newUser(User user) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.name, user.getName());
        cv.put(DatabaseHelper.email, user.getEmail());
        cv.put(DatabaseHelper.identityId, user.getIdentityId());
        cv.put(DatabaseHelper.password, user.getPw());
        cv.put(DatabaseHelper.type, user.getType());

        int id = (int) sqLiteDatabase.insert(DatabaseHelper.user, null, cv);
        user.setId(id);

        closeDatabase();
        return user;
    }

    public User getUser(int userId) {
        openDatabase();
        User user = null;

        String statement = "SELECT * FROM " + DatabaseHelper.user
                + " WHERE " + DatabaseHelper.id + " = " + userId;

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setIdentityId(cursor.getString(3));
            user.setPw(cursor.getString(4));
            user.setType(cursor.getInt(5));

        }

        cursor.close();
        closeDatabase();
        return user;
    }

    public Teacher getTeacher(int userId) {
        openDatabase();
        Teacher teacher = null;

        String statement = "SELECT * FROM " + DatabaseHelper.teacher
                + " WHERE " + DatabaseHelper.teacherId + " = " + userId;

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);
        if (cursor.moveToFirst()) {
            teacher = new Teacher();
            teacher.setId(cursor.getInt(0));
            teacher.setUserId(cursor.getInt(1));
            teacher.setName(cursor.getString(2));
            teacher.setSubject(cursor.getString(3));
            teacher.setSections(cursor.getString(4));
            teacher.setSchoolId(cursor.getInt(5));

        }

        cursor.close();
        closeDatabase();
        return teacher;
    }

    public boolean schoolValidation(String name) {
        openDatabase();
        boolean found = false;

        String statement = "SELECT " + DatabaseHelper.id + " FROM " + DatabaseHelper.school
                + " WHERE " + DatabaseHelper.name + " = '" + name + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            found = true;
        }

        cursor.close();
        closeDatabase();
        return found;
    }

    public School newSchool(School school) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.name, school.getName());

        int id = (int) sqLiteDatabase.insert(DatabaseHelper.school, null, cv);
        school.setId(id);

        closeDatabase();
        return school;
    }

    public List<School> getSchools() {
        openDatabase();
        List<School> schools = new ArrayList<>();

        String statement = "SELECT * FROM " + DatabaseHelper.school;

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                School school = new School();

                school.setId(cursor.getInt(0));
                school.setName(cursor.getString(1));

                schools.add(school);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return schools;
    }

    public void deleteSchool(Integer id) {
        openDatabase();
        String statement = "DELETE FROM " + DatabaseHelper.school + " WHERE " +
                DatabaseHelper.id + "=" + id;

        sqLiteDatabase.execSQL(statement);

        String statement2 = "DELETE FROM " + DatabaseHelper.student + " WHERE " +
                DatabaseHelper.schoolId + "=" + id;

        sqLiteDatabase.execSQL(statement2);

        String statement3 = "DELETE FROM " + DatabaseHelper.teacher + " WHERE " +
                DatabaseHelper.schoolId + "=" + id;

        sqLiteDatabase.execSQL(statement3);

        closeDatabase();
    }

    public boolean studentValidation(String studentNumber) {
        openDatabase();
        boolean found = false;

        String statement = "SELECT " + DatabaseHelper.id + " FROM " + DatabaseHelper.student
                + " WHERE " + DatabaseHelper.studentNumber + " = '" + studentNumber + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            found = true;
        }

        cursor.close();
        closeDatabase();
        return found;
    }

    public StudentInformation newStudent(StudentInformation studentInformation) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.name, studentInformation.getName());
        cv.put(DatabaseHelper.motherId, studentInformation.getMid());
        cv.put(DatabaseHelper.fatherId, studentInformation.getFid());
        cv.put(DatabaseHelper.level, studentInformation.getLev());
        cv.put(DatabaseHelper.section, studentInformation.getSec());
        cv.put(DatabaseHelper.studentNumber, studentInformation.getStudentid());
        cv.put(DatabaseHelper.studentStatus, studentInformation.getStatus());
        cv.put(DatabaseHelper.schoolId, studentInformation.getSchoolId());

        int id = (int) sqLiteDatabase.insert(DatabaseHelper.student, null, cv);
        studentInformation.setId(id);

        closeDatabase();
        return studentInformation;
    }

    public List<StudentInformation> getStudents(int schoolId) {
        openDatabase();
        List<StudentInformation> studentInformations = new ArrayList<>();

        String statement = "SELECT * FROM " + DatabaseHelper.student
                + " WHERE " + DatabaseHelper.schoolId + " = " + schoolId;

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                StudentInformation student = new StudentInformation();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setMid(cursor.getString(2));
                student.setFid(cursor.getString(3));
                student.setLev(cursor.getString(4));
                student.setSec(cursor.getString(5));
                student.setStudentid(cursor.getString(6));
                student.setStatus(cursor.getInt(7));
                student.setSchoolId(cursor.getInt(8));
                studentInformations.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return studentInformations;
    }

    public List<StudentInformation> getStudentsBySection(String section) {
        openDatabase();
        List<StudentInformation> studentInformations = new ArrayList<>();

        String statement = "SELECT * FROM " + DatabaseHelper.student
                + " WHERE " + DatabaseHelper.section + " = '" + section + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                StudentInformation student = new StudentInformation();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setMid(cursor.getString(2));
                student.setFid(cursor.getString(3));
                student.setLev(cursor.getString(4));
                student.setSec(cursor.getString(5));
                student.setStudentid(cursor.getString(6));
                student.setStatus(cursor.getInt(7));
                student.setSchoolId(cursor.getInt(8));
                studentInformations.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return studentInformations;
    }

    public StudentInformation getStudentsByNumber(String studentNumber) {
        openDatabase();
        StudentInformation student = null;

        String statement = "SELECT * FROM " + DatabaseHelper.student
                + " WHERE " + DatabaseHelper.studentNumber + " = '" + studentNumber + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            student = new StudentInformation();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setMid(cursor.getString(2));
            student.setFid(cursor.getString(3));
            student.setLev(cursor.getString(4));
            student.setSec(cursor.getString(5));
            student.setStudentid(cursor.getString(6));
            student.setStatus(cursor.getInt(7));
            student.setSchoolId(cursor.getInt(8));
        }

        closeDatabase();
        return student;
    }

    public List<StudentInformation> getStudentsByMotherId(String motherId) {
        openDatabase();
        List<StudentInformation> studentInformations = new ArrayList<>();

        String statement = "SELECT * FROM " + DatabaseHelper.student
                + " WHERE " + DatabaseHelper.motherId + " = '" + motherId + "'"
                + " AND " + DatabaseHelper.studentStatus + " = 1";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                StudentInformation student = new StudentInformation();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setMid(cursor.getString(2));
                student.setFid(cursor.getString(3));
                student.setLev(cursor.getString(4));
                student.setSec(cursor.getString(5));
                student.setStudentid(cursor.getString(6));
                student.setStatus(cursor.getInt(7));
                student.setSchoolId(cursor.getInt(8));
                studentInformations.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return studentInformations;
    }

    public void updateStudentStatus(StudentInformation studentInformation) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.studentStatus, 1);

        String selection = DatabaseHelper.id + " Like ?";
        String[] selection_args = {String.valueOf(studentInformation.getId())};

        sqLiteDatabase.update(DatabaseHelper.student, cv, selection, selection_args);
        closeDatabase();
    }

    public void deleteStudent(Integer id) {
        openDatabase();
        String statement = "DELETE FROM " + DatabaseHelper.student + " WHERE " +
                DatabaseHelper.id + "=" + id;

        sqLiteDatabase.execSQL(statement);

        closeDatabase();
    }

    public Teacher newTeacher(Teacher teacher) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.teacherId, teacher.getUserId());
        cv.put(DatabaseHelper.name, teacher.getName());
        cv.put(DatabaseHelper.subject, teacher.getSubject());
        cv.put(DatabaseHelper.section, teacher.getSections());
        cv.put(DatabaseHelper.schoolId, teacher.getSchoolId());

        int id = (int) sqLiteDatabase.insert(DatabaseHelper.teacher, null, cv);
        teacher.setId(id);

        closeDatabase();
        return teacher;
    }

    public List<Teacher> getTeachers(int schoolId) {
        openDatabase();
        List<Teacher> teachers = new ArrayList<>();

        String statement = "SELECT * FROM " + DatabaseHelper.teacher
                + " WHERE " + DatabaseHelper.schoolId + " = " + schoolId;

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                Teacher teacher = new Teacher();
                teacher.setId(cursor.getInt(0));
                teacher.setUserId(cursor.getInt(1));
                teacher.setName(cursor.getString(2));
                teacher.setSubject(cursor.getString(3));
                teacher.setSections(cursor.getString(4));
                teacher.setSchoolId(cursor.getInt(5));
                teachers.add(teacher);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return teachers;
    }

    public void deleteTeacher(Integer id) {
        openDatabase();
        String statement = "DELETE FROM " + DatabaseHelper.teacher + " WHERE " +
                DatabaseHelper.id + "=" + id;

        sqLiteDatabase.execSQL(statement);

        closeDatabase();
    }

    public Homework newHomework(Homework homework) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.teacherId, homework.getTeacherId());
        cv.put(DatabaseHelper.name, homework.getName());
        cv.put(DatabaseHelper.section, homework.getSection());
        cv.put(DatabaseHelper.subject, homework.getSubject());
        cv.put(DatabaseHelper.schoolId, homework.getSchoolId());

        int id = (int) sqLiteDatabase.insert(DatabaseHelper.homework, null, cv);
        homework.setId(id);

        closeDatabase();
        return homework;
    }

    public HomeworkAnswer newHomeworkAnswer(HomeworkAnswer homeworkAnswer) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.homeworkId, homeworkAnswer.getTeacherId());
        cv.put(DatabaseHelper.teacherId, homeworkAnswer.getTeacherId());
        cv.put(DatabaseHelper.studentId, homeworkAnswer.getTeacherId());
        cv.put(DatabaseHelper.answer, homeworkAnswer.getAnswer());
        cv.put(DatabaseHelper.section, homeworkAnswer.getSection());
        cv.put(DatabaseHelper.schoolId, homeworkAnswer.getSchoolId());

        int id = (int) sqLiteDatabase.insert(DatabaseHelper.homework, null, cv);
        homeworkAnswer.setId(id);

        closeDatabase();
        return homeworkAnswer;
    }

    public Rate newRate(Rate rate) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.teacherId, rate.getTeacherId());
        cv.put(DatabaseHelper.subject, rate.getSubject());
        cv.put(DatabaseHelper.studentId, rate.getTeacherId());
        cv.put(DatabaseHelper.readCat, rate.getReadCat());
        cv.put(DatabaseHelper.writeCat, rate.getWriteCat());
        cv.put(DatabaseHelper.saveCat, rate.getSaveCat());
        cv.put(DatabaseHelper.homeWorkCat, rate.getHomeworkCat());

        int id = (int) sqLiteDatabase.insert(DatabaseHelper.rating, null, cv);
        rate.setId(id);

        closeDatabase();
        return rate;
    }

    public Rate getSubjectRateForStudent(String subject, int studentId) {
        openDatabase();
        Rate rate = null;

        String statement = "SELECT * FROM " + DatabaseHelper.rating
                + " WHERE " + DatabaseHelper.subject + " = '" + subject + "'"
                + " AND " + DatabaseHelper.studentId + " = " + studentId
                + " AND " + DatabaseHelper.id +" = (SELECT MAX(" + DatabaseHelper.id + ") FROM " + DatabaseHelper.rating + ")";

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            rate = new Rate();
            rate.setId(cursor.getInt(0));
            rate.setTeacherId(cursor.getInt(1));
            rate.setSubject(cursor.getString(2));
            rate.setStudentId(cursor.getInt(3));
            rate.setReadCat(cursor.getString(4));
            rate.setWriteCat(cursor.getString(5));
            rate.setSaveCat(cursor.getString(6));
            rate.setHomeworkCat(cursor.getString(7));
        }

        closeDatabase();
        return rate;
    }

    public Homework getSubjectHomeWorkForStudent(String subject, String section) {
        openDatabase();
        Homework homework = null;

        String statement = "SELECT * FROM " + DatabaseHelper.homework
                + " WHERE " + DatabaseHelper.subject + " = '" + subject + "'"
                + " AND " + DatabaseHelper.section + " = '" + section + "'"
                + " AND " + DatabaseHelper.id +" = (SELECT MAX(" + DatabaseHelper.id + ") FROM " + DatabaseHelper.homework + ")" ;

        Cursor cursor = sqLiteDatabase.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            homework = new Homework();
            homework.setId(cursor.getInt(0));
            homework.setTeacherId(cursor.getInt(1));
            homework.setName(cursor.getString(2));
            homework.setSection(cursor.getString(3));
            homework.setSubject(cursor.getString(4));
            homework.setSchoolId(cursor.getInt(5));
        }

        closeDatabase();
        return homework;
    }
}
