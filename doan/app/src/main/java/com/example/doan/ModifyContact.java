package com.example.doan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyContact extends AppCompatActivity {
    private DBAdapter dbAdapter;
    private EditText editTextMAKH, editTextNAME, editTextPHONE, editTextaddr;
    private String MAKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_contacts);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        Intent intent = getIntent();
        MAKH = intent.getStringExtra("MAKH");

        editTextNAME = findViewById(R.id.editTextName);
        editTextaddr = findViewById(R.id.editTextAddress);
        editTextPHONE = findViewById(R.id.editTextPhone);
        editTextMAKH = findViewById(R.id.editTextMAKH);

        loadStudentData();

        Button buttonUpdate = findViewById(R.id.btnUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudentInfo();
            }
        });
    }

    private void loadStudentData() {
        Cursor cursor = dbAdapter.getUserByMAKH(MAKH);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DBAdapter.KEY_HOTEN));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DBAdapter.KEY_PHONE));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DBAdapter.KEY_ADDRESS));

            editTextNAME.setText(name);
            editTextMAKH.setText(MAKH);
            editTextPHONE.setText(phone);
            editTextaddr.setText(address);

            cursor.close();
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin khách hàng", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStudentInfo() {
        String name = editTextNAME.getText().toString();
        String phone = editTextPHONE.getText().toString();
        String addr = editTextaddr.getText().toString();

        dbAdapter.updateUser(MAKH, name, phone, addr);
        Toast.makeText(this, "Thông tin đã được cập nhật", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }
}

