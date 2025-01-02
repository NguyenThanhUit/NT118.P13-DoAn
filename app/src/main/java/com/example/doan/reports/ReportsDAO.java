package com.example.doan.reports;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReportsDAO {
    // Thêm một báo cáo mới
    @Insert
    void insertReport(Reports report);

    // Cập nhật một báo cáo
    @Update
    void updateReport(Reports report);

    // Xóa một báo cáo
    @Delete
    void deleteReport(Reports report);

    // Lấy tất cả báo cáo
    @Query("SELECT * FROM reports_information")
    LiveData<List<Reports>> getAllReports();
}