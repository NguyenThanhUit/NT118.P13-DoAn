package com.example.doan.domain.employee;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddNewEmployeeClick {

    private Employees employees;
    private Context context;
    private EmployeeViewModel employeesViewModel;

    public AddNewEmployeeClick() {
        super();
    }

    public AddNewEmployeeClick(Employees employees, Context context, EmployeeViewModel employeesViewModel) {
        this.employees = employees;
        this.context = context;
        this.employeesViewModel = employeesViewModel;
    }


    public void onSubmitBtnClicked(View view) {
        if (TextUtils.isEmpty(employees.getUsername()) ||
                TextUtils.isEmpty(employees.getPassword()) ||
                TextUtils.isEmpty(employees.getName()) ||
                TextUtils.isEmpty(employees.getPhone()) ||
                TextUtils.isEmpty(employees.getEmail()) ||
                TextUtils.isEmpty(employees.getPosition())) {
            Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            String currentDateTime = getCurrentDateTime();

            Employees newEmployee = new Employees(
                    employees.getPhone(),
                    employees.getName(),
                    employees.getPosition(),
                    employees.getEmail(),
                    employees.getUsername(),
                    currentDateTime,
                    employees.getPassword(),
                    currentDateTime
            );
            employeesViewModel.addnewEmployee(newEmployee);

            Toast.makeText(context, "Nhân viên đã được thêm thành công!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(System.currentTimeMillis());
    }
}
