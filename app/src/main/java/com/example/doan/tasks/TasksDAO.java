package com.example.doan.tasks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface TasksDAO {
    @Query("SELECT * FROM tasks_information")
    LiveData<List<Tasks>> getALLTasks();

    @Insert
    void insertTask(Tasks tasks);

    @Delete
    void deleteTask(Tasks tasks);

    @Query("SELECT * FROM tasks_information WHERE employee_id = :employeeId")
    LiveData<List<Tasks>> getTasksForEmployee(String employeeId);
}
