package com.example.doan.reports;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doan.database.Repository;

import java.util.List;

public class ReportsViewModel extends AndroidViewModel {

    public Repository repository;
    public LiveData<List<Reports>> allreports;

    public ReportsViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        allreports = repository.getAllReports();
    }

    public LiveData<List<Reports>> getAllreports(){
        return  allreports;
    }

    public  void addnewReport(Reports reports){
        repository.addnewReport(reports);
    }
    public void deleteReport(Reports reports){
        repository.deleteReport(reports);
    }
    public void updateReport(Reports report) { repository.updateReport(report); }
}
