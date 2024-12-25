package com.example.doan.domain.admin;

import android.content.Intent;
import android.net.eap.EapSessionConfig;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;

public class AdminTaskList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_task_list);

        ImageButton icBack = findViewById(R.id.ic_back);
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the previous one
                finish();
            }
        });

        ImageButton btnEmployeeAdd = findViewById(R.id.ib_task_add);
        btnEmployeeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to NextActivity
                Intent intent = new Intent(AdminTaskList.this, AdminTaskCreate.class);
                startActivity(intent);
            }
        });
    }
}
