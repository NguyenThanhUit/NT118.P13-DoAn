package com.example.doan.domain.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;
import com.example.doan.domain.auth.LogInActivity;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

        Button btnManageEmployee = findViewById(R.id.btnManageEmployee);
        btnManageEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, ManageEmployeeActivity.class);
                startActivity(intent);
            }
        });

        Button btnAssignmentTask = findViewById(R.id.btnAssignmentTask);
        btnAssignmentTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AssignTaskActivity.class);
                startActivity(intent);
            }
        });
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}