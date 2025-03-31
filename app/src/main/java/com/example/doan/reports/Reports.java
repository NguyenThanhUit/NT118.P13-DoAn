package com.example.doan.reports;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reports reports = (Reports) o;
        return Objects.equals(reportID, reports.reportID) && Objects.equals(reportTitle, reports.reportTitle) && Objects.equals(reportContent, reports.reportContent) && Objects.equals(startDate, reports.startDate) && Objects.equals(endDate, reports.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportID, reportTitle, reportContent, startDate, endDate);
    }
}