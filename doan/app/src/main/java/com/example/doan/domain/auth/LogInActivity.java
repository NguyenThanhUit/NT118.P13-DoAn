package com.example.doan.domain.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.MainActivity;
import com.example.doan.R;
import com.example.doan.domain.employee.AdminHomeActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LogInActivity extends AppCompatActivity {

    TextInputEditText textUsername;
    String isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        textUsername = findViewById(R.id.tie_username);

        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAdmin = textUsername.getText().toString();
                if (isAdmin.equals("admin")) {
                    Intent intent = new Intent(LogInActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
