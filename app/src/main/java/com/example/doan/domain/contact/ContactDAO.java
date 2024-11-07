package com.example.doan.domain.contact;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.doan.domain.contact.ContactDto;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    void insert(ContactEntity contacts);

    @Delete
    void delete(ContactEntity contacts);

    //Tu tao query
    @Query("SELECT * FROM contacts_table")
    LiveData<List<ContactEntity>> getAllContacts();
}