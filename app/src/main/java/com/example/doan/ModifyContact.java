package com.example.doan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.doan.data.DBAdapter;

public class ModifyContact extends AppCompatActivity {
    private DBAdapter dbAdapter;
    private EditText editTextNAME, editTextPHONE, editTextaddr;
    private TextView textViewMAKH, textViewNAME, textViewPHONE, textViewaddr;
    private String MAKH;
    private Button btnEdit, buttonUpdate;
    private ImageButton ibtnBack;

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
        textViewNAME = findViewById(R.id.textViewName);


        textViewMAKH = findViewById(R.id.textViewMAKH);
        textViewPHONE = findViewById(R.id.tvPhone);
        textViewaddr = findViewById(R.id.tvAddress);

        btnEdit = findViewById(R.id.btnedit);
        buttonUpdate = findViewById(R.id.btnUpdate);
        ibtnBack = findViewById(R.id.ibtnBack);

        loadStudentData();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudentInfo();
            }
        });

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
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
            textViewMAKH.setText(MAKH);
            editTextPHONE.setText(phone);
            editTextaddr.setText(address);

            textViewNAME.setText(name);
            textViewPHONE.setText(phone);
            textViewaddr.setText(address);

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
        textViewPHONE.setText(phone);
        textViewaddr.setText(addr);

        setResult(RESULT_OK);
        finish();
    }

    private void toggleEditMode() {
        boolean isEditMode = editTextNAME.getVisibility() == View.GONE;

        if (isEditMode) {
            editTextNAME.setText(textViewNAME.getText());
            editTextPHONE.setText(textViewPHONE.getText());
            editTextaddr.setText(textViewaddr.getText());

            editTextNAME.setVisibility(View.VISIBLE);
            editTextPHONE.setVisibility(View.VISIBLE);
            editTextaddr.setVisibility(View.VISIBLE);

            textViewNAME.setVisibility(View.GONE);
            textViewPHONE.setVisibility(View.GONE);
            textViewaddr.setVisibility(View.GONE);

            btnEdit.setText("View");
        } else {
            textViewNAME.setText(editTextNAME.getText().toString());
            textViewPHONE.setText(editTextPHONE.getText().toString());
            textViewaddr.setText(editTextaddr.getText().toString());

            editTextNAME.setVisibility(View.GONE);
            editTextPHONE.setVisibility(View.GONE);
            editTextaddr.setVisibility(View.GONE);

            textViewNAME.setVisibility(View.VISIBLE);
            textViewPHONE.setVisibility(View.VISIBLE);
            textViewaddr.setVisibility(View.VISIBLE);

            btnEdit.setText("Edit");
        }
    }
}
