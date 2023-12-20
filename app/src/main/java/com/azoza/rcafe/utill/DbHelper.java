package com.azoza.rcafe.utill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.azoza.rcafe.model.User;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "r_cafe_db";
    private static final int DB_VERSION = 1;

    private static String TABLE_NAME = "User";

    private static final String USER_TABLE_ID = "id";

    private static final String USER_TABLE_NAME = "name";

    private static final String USER_TABLE_EMAIL = "email";

    private static final String USER_TABLE_PASSWORD = "password";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,email VARCHAR,password VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_TABLE_NAME, user.getName());
        values.put(USER_TABLE_EMAIL, user.getEmail());
        values.put(USER_TABLE_PASSWORD, user.getPassword());

        long id = db.insert(TABLE_NAME, null, values);
        //db.close();
        return id;

    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                int userIdIndex = cursor.getColumnIndex("id");
                int userNameIndex = cursor.getColumnIndex("name");
                int userEmailIndex = cursor.getColumnIndex("email");
                int userPasswordIndex = cursor.getColumnIndex("password");
                if (userIdIndex >= 0) {
                    user.setId(cursor.getInt(userIdIndex));
                }
                if (userNameIndex >= 0) {
                    user.setName(cursor.getString(userNameIndex));
                }
                if (userEmailIndex >= 0) {
                    user.setEmail(cursor.getString(userEmailIndex));
                }
                if (userPasswordIndex >= 0) {
                    user.setPassword(cursor.getString(userPasswordIndex));
                }
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return users;

    }
}
