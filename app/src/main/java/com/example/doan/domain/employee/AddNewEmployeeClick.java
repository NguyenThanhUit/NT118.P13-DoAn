package com.example.doan.domain.employee;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
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
            Toast.makeText(context, "Please fill in all information!", Toast.LENGTH_SHORT).show();
        } else {
            employeesViewModel.checkUsernameExists(employees.getUsername()).observe((LifecycleOwner) context, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isTaken) {
                    if (isTaken) {
                        Toast.makeText(context, "Username already exists!", Toast.LENGTH_SHORT).show();
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

                        Toast.makeText(context, "Add new employee successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ManageEmployeeActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(System.currentTimeMillis());
    }
}
