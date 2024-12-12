package com.example.doan.domain.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;
import com.example.doan.domain.employee.ManageEmployeeActivity;

public class AdminAddEmployee extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_employee);
        ImageButton backButton = findViewById(R.id.ic_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddEmployee.this, ManageEmployeeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
