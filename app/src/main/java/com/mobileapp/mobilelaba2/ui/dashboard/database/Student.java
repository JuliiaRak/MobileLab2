package com.mobileapp.mobilelaba2.ui.dashboard.database;

import android.content.ContentValues;

public class Student {
    private int id;
    private String pib;
    private String grade1;
    private String grade2;
    private String adress;

    public Student(){
    }

    public Student(String pib, String grade1, String grade2, String adress) {
        this.pib = pib;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.adress = adress;
    }

    public Student(int Id, String pib, String grade1, String grade2, String adress) {
        this.id = id;
        this.pib = pib;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getGrade1() {
        return grade1;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public String getGrade2() {
        return grade2;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
