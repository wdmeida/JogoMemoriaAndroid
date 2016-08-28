package com.pdm.ifsudestemg.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wagner on 19/08/16.
 */
public class CreateDatabase extends SQLiteOpenHelper {
    public static final String TABLE_USER = "usuarios";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nome";
    public static final String COLUMN_POINTS = "pontos";

    private static final String DATABASE_NAME = "memorygame.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_USER + "(" +
                                                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    COLUMN_NAME + " TEXT NOT NULL, " +
                                                    COLUMN_POINTS + " INTEGER NOT NULL);";

    public CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }
}
