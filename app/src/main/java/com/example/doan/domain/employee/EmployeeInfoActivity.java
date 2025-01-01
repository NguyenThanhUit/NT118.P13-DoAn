package com.example.doan.domain.employee;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;

public class EmployeeInfoActivity extends AppCompatActivity {

    TextView tvRole, tvEmployeeName, tvEmail, tvPhoneNumber;
    EditText etName, etPhone, etEmail;
    Button btnUpdate;
    ImageButton icBack, icEdit;
    EmployeeViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        tvRole = findViewById(R.id.tv_role);
        tvEmployeeName = findViewById(R.id.tv_employee_name);
        tvEmail = findViewById(R.id.tv_email);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);

        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);

        btnUpdate = findViewById(R.id.btn_update);

        icBack = findViewById(R.id.ic_back);
        icEdit = findViewById(R.id.ic_edit);

        etName.setVisibility(View.GONE);
        etPhone.setVisibility(View.GONE);
        etEmail.setVisibility(View.GONE);

        myViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        icBack.setOnClickListener(view -> finish());

        icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUpdate.setVisibility(View.VISIBLE);

                etName.setVisibility(View.VISIBLE);
                etPhone.setVisibility(View.VISIBLE);
                etEmail.setVisibility(View.VISIBLE);

                tvEmployeeName.setText("Name");
                tvEmail.setText("Email");
                tvPhoneNumber.setText("Phone Number");
            }
        });

        // Nhận dữ liệu từ intent
        String employeeId = getIntent().getStringExtra("employeeId");
        String employeeName = getIntent().getStringExtra("employeeName");
        String role = getIntent().getStringExtra("role");
        String email = getIntent().getStringExtra("email");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        // Kiểm tra giá trị null để tránh lỗi crash
        if (employeeName != null) tvEmployeeName.setText("Name: " + employeeName);
        if (role != null) tvRole.setText(role);
        if (email != null) tvEmail.setText("Email: " + email);
        if (phoneNumber != null) tvPhoneNumber.setText("Phone Number: " + phoneNumber);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = etName.getText().toString().trim();
                String newPhone = etPhone.getText().toString().trim();
                String newEmail = etEmail.getText().toString().trim();

                if(!newName.isEmpty() && !newPhone.isEmpty() && !newEmail.isEmpty()){
                    Employees updatedEmployee = new Employees();
                    updatedEmployee.setEid(employeeId);
                    updatedEmployee.setName(newName);
                    updatedEmployee.setPhone(newPhone);
                    updatedEmployee.setEmail(newEmail);
                    myViewModel.updateEmployee(updatedEmployee);

                    Toast.makeText(EmployeeInfoActivity.this, "Information updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EmployeeInfoActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}