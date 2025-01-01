package com.example.doan.tasks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doan.database.Repository;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    public Repository repository;
    public LiveData<List<Tasks>> alltasks;
    public TasksViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        alltasks = repository.getALLTasks();
    }
    public  void addnewTask(Tasks tasks){
        repository.addnewTask(tasks);
    }
    public void deleteTask(Tasks tasks){
        repository.deleteTask(tasks);
    }
    public LiveData<List<Tasks>> getAlltasks(){
        return alltasks;
    }

    public void deleteTaskID(Tasks task) {
        repository.deleteTaskID(task);
    }
}
