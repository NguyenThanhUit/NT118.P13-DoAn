package com.example.doan.domain.employee;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Optional;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM t_employee")
    LiveData<List<EmployeeEntity>> findAll();

    @Query("SELECT * FROM t_employee as t where t.id = :id")
    LiveData<Optional<EmployeeEntity>> findById(Long id);

    @Insert
    void insert(EmployeeEntity newEmployee);

    @Update
    EmployeeEntity update(EmployeeEntity updatedEmployee);

    @Delete
    void delete(Long id);
}