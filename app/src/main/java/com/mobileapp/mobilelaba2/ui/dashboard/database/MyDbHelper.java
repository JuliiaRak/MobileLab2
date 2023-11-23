package com.mobileapp.mobilelaba2.ui.dashboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyConstants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MyConstants.DROP_TABLE);
        onCreate(db);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.COLUMN_NAME_PIB, student.getPib());
        cv.put(MyConstants.COLUMN_NAME_GRADE1, student.getGrade1());
        cv.put(MyConstants.COLUMN_NAME_GRADE2, student.getGrade2());
        cv.put(MyConstants.COLUMN_NAME_ADDRESS, student.getAdress());

        db.insert(MyConstants.TABLE_NAME, null, cv);
        db.close();
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MyConstants.TABLE_NAME, MyConstants.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(studentId)});
        db.close();
    }

    public Student getStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                new String[] {MyConstants.COLUMN_NAME_ID, MyConstants.COLUMN_NAME_PIB,
                        MyConstants.COLUMN_NAME_GRADE1, MyConstants.COLUMN_NAME_GRADE2,
                        MyConstants.COLUMN_NAME_ADDRESS},
                MyConstants.COLUMN_NAME_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                 cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
        db.close();
        return student;
    }

    public List<Student> getAllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Student> studentList = new ArrayList<>();

        String selectAllStudents = "SELECT * FROM " + MyConstants.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStudents, null);

        if(cursor.moveToFirst()){
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setPib(cursor.getString(1));
                student.setGrade1(cursor.getString(2));
                student.setGrade2(cursor.getString(3));
                student.setAdress(cursor.getString(4));
                studentList.add(student);
            } while(cursor.moveToNext());
        }
        return studentList;
    }

    public List<Student> getStudentsAbove60() {
        List<Student> studentsAbove60 = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Запит, щоб вибрати всіх студентів із середнім балом більше 60
        String selectQuery = "SELECT * FROM " + MyConstants.TABLE_NAME +
                " WHERE ((" + MyConstants.COLUMN_NAME_GRADE1 + " + " +
                MyConstants.COLUMN_NAME_GRADE2 + ") / 2) > 60";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setPib(cursor.getString(1));
                student.setGrade1(cursor.getString(2));
                student.setGrade2(cursor.getString(3));
                student.setAdress(cursor.getString(4));
                studentsAbove60.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return studentsAbove60;
    }

}
