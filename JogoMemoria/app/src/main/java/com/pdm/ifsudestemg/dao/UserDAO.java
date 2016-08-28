package com.pdm.ifsudestemg.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pdm.ifsudestemg.model.User;
import com.pdm.ifsudestemg.persistence.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 21/08/16.
 */
public class UserDAO {
    private SQLiteDatabase sqLiteDatabase;
    private CreateDatabase database;
    private String[] columns = {CreateDatabase.COLUMN_ID,
                                CreateDatabase.COLUMN_NAME,
                                CreateDatabase.COLUMN_POINTS};

    public UserDAO(Context context) {
        database = new CreateDatabase(context);
    }

    public long insert(User user) {
        //Cria um objeto ContentValues para poder inserir o conteúdo.
        ContentValues values = new ContentValues();

        values.put(CreateDatabase.COLUMN_NAME, user.getName());
        values.put(CreateDatabase.COLUMN_POINTS, user.getPoints());

        sqLiteDatabase = database.getWritableDatabase();

        long resultado = sqLiteDatabase.insert(CreateDatabase.TABLE_USER, null, values);
        database.close();
        return resultado;
    }//insert()

    public List<User> getAll() {
        sqLiteDatabase = database.getReadableDatabase();
        List<User> users = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(CreateDatabase.TABLE_USER, columns, null, null, null, null, " pontos ASC");

        if (cursor.moveToFirst()) {
            do{
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(CreateDatabase.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NAME)));
                user.setPoints(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_POINTS)));
                users.add(user);
            }while (cursor.moveToNext());
        }
        database.close();
        return users;
    }//getAll()

    public void deleteAll(){
        sqLiteDatabase = database.getWritableDatabase();
        sqLiteDatabase.delete(CreateDatabase.TABLE_USER, null, null);
    }
}
