package com.example.doan.reports;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.doan.domain.employee.Employees;

@Entity(
        tableName = "reports_information",
        foreignKeys = @ForeignKey(
                entity = Employees.class,
                parentColumns = "employee_id",
                childColumns = "employee_id",
                onDelete = ForeignKey.CASCADE //Xoa bao cao khi nhan vien bi xoa
        )
)
public class Reports {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "report_id")
    private String reportID;

    @ColumnInfo(name = "report_title")
    private String reportTitle;

    @ColumnInfo(name = "report_content")
    private String reportContent;

    @ColumnInfo(name = "report_createdAT")
    private String createdAT;

    @ColumnInfo(name = "employee_id")
    private String employeeID;

    // Constructors, Getters và Setters
    public Reports(@NonNull String reportID, String reportTitle, String reportContent, String createdAT, String employeeID) {
        this.reportID = reportID;
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
        this.createdAT = createdAT;
        this.employeeID = employeeID;
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

    public String getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(String createdAT) {
        this.createdAT = createdAT;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
