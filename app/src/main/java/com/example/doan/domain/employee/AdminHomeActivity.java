package com.example.doan.domain.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;
import com.example.doan.tasks.AssignTaskActivity;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button btnManageEmployee = findViewById(R.id.btnManageEmployee);
        if (btnManageEmployee != null) {
            btnManageEmployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AdminHomeActivity.this, ManageEmployeeActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Khởi tạo Button phân công công việc
        Button btnAssignmentTask = findViewById(R.id.btnAssignmentTask);
        if (btnAssignmentTask != null) {
            btnAssignmentTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AdminHomeActivity.this, AssignTaskActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
