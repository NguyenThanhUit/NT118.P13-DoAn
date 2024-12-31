package com.example.doan.reports;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reports_information")
public class Reports {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "report_id")
    private String reportID;

    @ColumnInfo(name = "report_title")
    private String reportTitle;

    @ColumnInfo(name = "report_content")
    private String reportContent;

    @ColumnInfo(name = "report_start_date")
    private String startDate;  // Ngày bắt đầu

    @ColumnInfo(name = "report_end_date")
    private String endDate;    // Ngày kết thúc

    // Constructors, Getters và Setters
    public Reports(@NonNull String reportID, String reportTitle, String reportContent, String startDate, String endDate) {
        this.reportID = reportID;
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @NonNull
    public String getReportID() {
        return reportID;
    }

    public void setReportID(@NonNull String reportID) {
        this.reportID = reportID;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
