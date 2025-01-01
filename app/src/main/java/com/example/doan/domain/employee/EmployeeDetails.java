package com.example.doan.domain.employee;

import androidx.room.ColumnInfo;

public class EmployeeDetails {
    @ColumnInfo(name = "employee_id")
    public String employeeId;

    @ColumnInfo(name = "employee_name")
    public String employeeName;

    @ColumnInfo(name = "employee_phone")
    public String employeePhone;

    @ColumnInfo(name = "employee_email")
    public String employeeEmail;

    @ColumnInfo(name = "totalOrder")
    public float totalOrder;
}


