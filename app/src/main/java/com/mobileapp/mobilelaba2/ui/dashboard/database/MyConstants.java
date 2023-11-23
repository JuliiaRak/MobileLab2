package com.mobileapp.mobilelaba2.ui.dashboard.database;

public class MyConstants {
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "students";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_PIB = "_pib";
    public static final String COLUMN_NAME_GRADE1 = "_grade1";
    public static final String COLUMN_NAME_GRADE2 = "_grade2";
    public static final String COLUMN_NAME_ADDRESS = "_address";

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_PIB + " TEXT," +
                    COLUMN_NAME_GRADE1 + " TEXT," +
                    COLUMN_NAME_GRADE2 + " TEXT," +
                    COLUMN_NAME_ADDRESS + " TEXT)";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
