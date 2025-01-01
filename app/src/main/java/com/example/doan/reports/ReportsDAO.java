package com.example.doan.reports;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReportsDAO {
    @Query("SELECT * FROM REPORTS_INFORMATION")
    LiveData<List<Reports>> getALlReports();

    @Insert
    void insertReport(Reports reports);

    @Delete
    void deleteReport(Reports reports);


}
