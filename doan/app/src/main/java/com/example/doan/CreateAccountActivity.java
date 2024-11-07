package com.example.doan;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        Spinner countryCodeSpinner = findViewById(R.id.country_code_spinner);

        // Tạo ArrayAdapter với tài nguyên country_code_options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.dialing_code, // Mảng chứa +84
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Thiết lập adapter cho Spinner
        countryCodeSpinner.setAdapter(adapter);
        // Xử lý sự kiện khi nhấn vào nút back
        ImageView backButton = findViewById(R.id.ic_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại màn hình admin_activity_setting
                finish(); // Đóng Create_Account_Activity và quay lại Activity trước đó
            }
        });
        // Xử lý sự kiện khi nhấn vào nút SignUp
    }

}
