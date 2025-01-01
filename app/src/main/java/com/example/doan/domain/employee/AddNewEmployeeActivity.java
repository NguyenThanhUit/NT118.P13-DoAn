package com.example.doan.domain.employee;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;
import com.example.doan.database.InfoDatabase;
import com.example.doan.databinding.CreateAccountBinding;

public class AddNewEmployeeActivity extends AppCompatActivity {
    private CreateAccountBinding createAccountBinding;
    private AddNewEmployeeClick handlers;
    private Employees employees;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);


        employees = new Employees();

        createAccountBinding = DataBindingUtil.setContentView(this, R.layout.create_account);


        handlers = new AddNewEmployeeClick(employees, this, employeeViewModel);

        createAccountBinding.setEmployee(employees);
        createAccountBinding.setClickHandler(handlers);

        InfoDatabase db = InfoDatabase.getInstance(this);
        Spinner spinnerPositionList = findViewById(R.id.position_input);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.employee_position,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPositionList.setAdapter(adapter);
        spinnerPositionList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedPosition = (String) adapterView.getItemAtPosition(i);
                employees.setPosition(selectedPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        createAccountBinding.setEmployee(employees);
        createAccountBinding.setClickHandler(handlers);
    }
}
