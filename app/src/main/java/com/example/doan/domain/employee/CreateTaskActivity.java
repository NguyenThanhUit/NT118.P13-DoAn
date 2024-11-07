package com.example.doan.domain.employee;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;

import java.util.Arrays;
import java.util.List;

public class CreateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_task);

        List<String> employeeList = Arrays.asList("001. Nguyễn Văn A", "002. Nguyễn Văn A", "003. Nguyễn Văn A", "004. Nguyễn Văn A", "005. Nguyễn Văn A");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                employeeList
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerEmployeeList = findViewById(R.id.spinnerEmployeeList);
        spinnerEmployeeList.setAdapter(adapter);

        ImageButton imgBtnBack4 = findViewById(R.id.imgBtnBack4);
        imgBtnBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}