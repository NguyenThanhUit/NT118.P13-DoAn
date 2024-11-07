package com.example.doan.domain.contact;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.doan.domain.contact.ContactDAO;
import com.example.doan.domain.contact.ContactEntity;

@Database(entities = {ContactEntity.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();

    //Singleton Pattern
    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ContactDatabase.class,
                            "contacts_db")
                    .fallbackToDestructiveMigration()  // Thêm phương thức này trước build()
                    .build();
        }
        return dbInstance;
    }



}