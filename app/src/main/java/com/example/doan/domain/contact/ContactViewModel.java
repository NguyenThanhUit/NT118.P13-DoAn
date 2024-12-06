package com.example.doan.domain.contact;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ContactViewModel extends AndroidViewModel {
    private ContactRepository myContactRepository;

    private LiveData<List<ContactEntity>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        this.myContactRepository = new ContactRepository(application);
    }

    public LiveData<List<ContactEntity>> getAllContacts(){
        allContacts = myContactRepository.getAllContacts();
        return allContacts;
    }
    public  void addnewContact(ContactEntity contacts){
        myContactRepository.addContact(contacts);
    }
    public void deleteContact(ContactEntity contacts){
        myContactRepository.deleteContact(contacts);
    }
}