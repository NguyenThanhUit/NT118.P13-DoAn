package com.example.doan.orders;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doan.database.Repository;
import com.example.doan.domain.employee.EmployeeDetails;

import java.util.List;

public class OrdersViewModel extends AndroidViewModel {
    public Repository repository;
    public LiveData<List<Orders>> allOders;

    public OrdersViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        allOders = repository.getALLOrders();
    }
    public void addnewOrdcer(Orders orders){
        repository.addnewOrders(orders);
    }
    public void deleteOrder(Orders orders){
        repository.deleteOrders(orders);
    }
    public LiveData<List<Orders>> getAllOrders (){
        return allOders;
    }
    public LiveData<Float> getTotalOrderRevenue() {
        return repository.getTotalOrderRevenue();
    }
    public LiveData<List<EmployeeDetails>> getTopEmployeesWithDetails() {
        return repository.getTopEmployeesWithDetails();
    }
}