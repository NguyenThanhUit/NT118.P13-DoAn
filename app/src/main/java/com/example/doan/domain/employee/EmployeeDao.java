package com.example.doan.domain.employee;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM EMPLOYEES_INFORMATION")
    LiveData<List<Employees>> getALLEmloyees();


    @Insert
    void insertEmployeee(Employees employees);

    @Delete
    void deleteEmployee(Employees employees);

    @Query("SELECT * FROM EMPLOYEES_INFORMATION WHERE employee_username = :username LIMIT 1")
    LiveData<List<Employees>> getEmployeesByUsername(String username);

    @Query("SELECT *  FROM employees_information")
    LiveData<List<Employees>> getallEID();

    @Update
    void updateEmployee(Employees employees);
}