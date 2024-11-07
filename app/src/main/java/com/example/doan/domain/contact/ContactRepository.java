package com.example.doan.domain.contact;

import android.app.Application;
import android.os.Looper;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;

import androidx.lifecycle.LiveData;


public class ContactRepository {
    ExecutorService executor;
    Handler handler;


    private final ContactDAO contactDAO;

    public ContactRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();
        //Use for Background Database Operations
        executor = Executors.newSingleThreadExecutor();
        //Use for updating UI
        handler = new Handler(Looper.getMainLooper());

    }

    public void addContact(ContactEntity contacts){
        //Tránh bị lỗi quá tải do vừa thêm thì có sự tương tác của user
        //Use for Background Database Operations
        executor = Executors.newSingleThreadExecutor();
        //Use for updating UI
        handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contacts);
            }
        });



    }
    public void deleteContact(ContactEntity contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });
    }
    public LiveData<List<ContactEntity>> getAllContacts(){
        return contactDAO.getAllContacts();
    }
}