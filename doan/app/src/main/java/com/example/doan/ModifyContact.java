package com.example.doan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyContact extends AppCompatActivity {
    private DBAdapter dbAdapter;
    private EditText editTextMAKH, editTextNAME, editTextPHONE, editTextaddr;
    private TextView textViewMAKH, textViewNAME, textViewPHONE, textViewaddr;
    private String MAKH;
    private Button btnedit, buttonUpdate;

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
        textViewNAME = findViewById(R.id.textViewName);
        textViewMAKH = findViewById(R.id.textViewMAKH);
        btnedit = findViewById(R.id.btnedit);
        buttonUpdate = findViewById(R.id.btnUpdate);

        loadStudentData();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudentInfo();
            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditMode();
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

            textViewNAME.setText(name);
            textViewMAKH.setText(MAKH);

            cursor.close();
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin khách hàng", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStudentInfo() {
        String name = editTextNAME.getText().toString();
        String phone = editTextPHONE.getText().toString();
        String addr = editTextaddr.getText().toString();

        dbAdapter.updateUser(MAKH, name, phone, addr);
        Toast.makeText(this, "Thông tin đã được cập nhật", Toast.LENGTH_SHORT).show();

        textViewNAME.setText(name);
        textViewMAKH.setText(MAKH);

        setResult(RESULT_OK);
        finish();
    }

    private void toggleEditMode() {
        if (editTextNAME.getVisibility() == View.GONE) {

            editTextNAME.setVisibility(View.VISIBLE);
            editTextMAKH.setVisibility(View.VISIBLE);
            textViewNAME.setVisibility(View.GONE);
            textViewMAKH.setVisibility(View.GONE);
            btnedit.setText("Save");
        } else {

            editTextNAME.setVisibility(View.GONE);
            editTextMAKH.setVisibility(View.GONE);
            textViewNAME.setVisibility(View.VISIBLE);
            textViewMAKH.setVisibility(View.VISIBLE);
            textViewNAME.setText(editTextNAME.getText());
            textViewMAKH.setText(editTextMAKH.getText());
            btnedit.setText("OK");
        }
    }
}
