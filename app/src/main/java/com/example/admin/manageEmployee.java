package com.example.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class manageEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_employee);

        ListView lvCustomers = findViewById(R.id.lvCustomers);
        ArrayList<String> customerList = new ArrayList<>();
        customerList.add("001. Nguyễn Văn A");
        customerList.add("002. Nguyễn Văn A");
        customerList.add("003. Nguyễn Văn A");
        customerList.add("004. Nguyễn Văn A");
        customerList.add("005. Nguyễn Văn A");
        customerList.add("006. Nguyễn Văn A");
        customerList.add("007. Nguyễn Văn A");
        customerList.add("008. Nguyễn Văn A");
        customerList.add("009. Nguyễn Văn A");
        customerList.add("010. Nguyễn Văn A");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                customerList
        );

        lvCustomers.setAdapter(adapter);

        ImageButton imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manageEmployee.this, createAccountEmployee.class);
                startActivity(intent);
            }
        });
    }
}