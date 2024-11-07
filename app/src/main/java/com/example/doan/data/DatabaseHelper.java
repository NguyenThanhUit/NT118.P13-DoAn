package com.example.doan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Cập nhật lệnh tạo bảng với cột status
    private static final String DATABASE_CREATE =
            "CREATE TABLE Contacts ("
                    + "MAKH TEXT PRIMARY KEY, "
                    + "NAME TEXT NOT NULL, "
                    + "PHONENUMBER TEXT NOT NULL, "
                    + "ADDRESS TEXT NOT NULL, "
                    + "status TEXT);"; // Thêm cột status
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contacts");
        onCreate(sqLiteDatabase);
    }

}
