package com.example.doan.customers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doan.database.Repository;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {
    private final Repository repository;
    private final LiveData<List<Customers>> allcustomer;


    public CustomerViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        allcustomer = repository.getAllCustomers();
    }
    public LiveData<List<Customers>> getAllcustomer(){
        return  allcustomer;
    }
    public void addnewCustomer(Customers customer){
        repository.addCustomer(customer);
    }
    public void deleteALLCustomer(){
        repository.deleteAllCustomer();
    }
    public void deleteCustomer(Customers customer) {
        repository.deleteCustomer(customer);
    }

    public void updateCustomer(Customers customers) {
        repository.updateCustomer(customers);
    }
    public LiveData<Integer> getCustomerCountByCategory(String category) {
        return repository.getCustomerCountByCategory(category);
    }

    public LiveData<Integer> getTotalCustomerCount() {
        return repository.getTotalCustomerCount();
    }
}
