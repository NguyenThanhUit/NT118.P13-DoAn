package com.example.doan.domain.employee;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;
import com.google.android.material.textfield.TextInputEditText;

public class EmployeeInfoActivity extends AppCompatActivity {
    private TextInputEditText etEmployeeId, etEmail, etPhoneNumber;
    private ImageView icEdit;
    private boolean isEditing = false; // Track edit state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        // Initialize Views
        etEmployeeId = findViewById(R.id.et_employee_id);
        etEmail = findViewById(R.id.et_employee_email);
        etPhoneNumber = findViewById(R.id.et_employee_phone_number);
        icEdit = findViewById(R.id.ic_edit);

        // Set OnClickListener for Edit Button
        icEdit.setOnClickListener(v -> toggleEditing());
    }

    private void toggleEditing() {
        isEditing = !isEditing;

        // Toggle fields' editable state
        etEmployeeId.setEnabled(isEditing);
        etEmail.setEnabled(isEditing);
        etPhoneNumber.setEnabled(isEditing);

        // Change icon based on edit state
        if (isEditing) {
            icEdit.setImageResource(R.drawable.ic_check);
        } else {
            icEdit.setImageResource(R.drawable.ic_edit);
            saveEmployeeInfo(); // Optionally save changes
        }
    }

    private void saveEmployeeInfo() {
        // Implement saving logic here if needed
        String employeeId = etEmployeeId.getText().toString();
        String email = etEmail.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        // You could save this data to a database or update it in your model
        Toast.makeText(this, "Employee Info Saved!", Toast.LENGTH_SHORT).show();
    }
}
