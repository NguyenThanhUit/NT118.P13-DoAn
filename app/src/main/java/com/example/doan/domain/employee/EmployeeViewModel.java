package com.example.doan.domain.employee;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doan.database.Repository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    public Repository repository;
    public LiveData<List<Employees>> allemployees;
    private LiveData<Boolean> isUsernameTaken;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        allemployees = repository.getAllEmployees();
    }

    public LiveData<List<Employees>> getAllemployees() {
        return allemployees;
    }

    public void addnewEmployee(Employees employees) {
        repository.addEmployee(employees);
    }

    public void deleteEmployee(Employees employees) {
        repository.deleteEmployee(employees);
    }

    public void updateEmployee(Employees employees) {
        repository.updateEmployee(employees);
    }

    public LiveData<Boolean> checkUsernameExists(String username) {
        if (isUsernameTaken == null) {
            isUsernameTaken = repository.checkUsernameExists(username);
        }
        return isUsernameTaken;
    }

}
