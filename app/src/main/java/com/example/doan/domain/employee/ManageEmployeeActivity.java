package com.example.doan.domain.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;

import java.util.ArrayList;

public class ManageEmployeeActivity extends AppCompatActivity {

    int selectedPosition = -1;
    ListView lvEmployees;
    ArrayList<String> employeeList;
    ArrayAdapter<String> adapter;
    Button btnDelete0, btnEdit0;
    ImageButton imgBtnBack;
    Button btnAdd0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_employee);

        lvEmployees = findViewById(R.id.lvEmployees);
        employeeList = new ArrayList<>();

        employeeList.add("001. Nguyễn Văn A");
        employeeList.add("002. Nguyễn Văn A");
        employeeList.add("003. Nguyễn Văn A");
        employeeList.add("004. Nguyễn Văn A");
        employeeList.add("005. Nguyễn Văn A");
        employeeList.add("006. Nguyễn Văn A");
        employeeList.add("007. Nguyễn Văn A");
        employeeList.add("008. Nguyễn Văn A");
        employeeList.add("009. Nguyễn Văn A");
        employeeList.add("010. Nguyễn Văn A");

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                employeeList
        );
        lvEmployees.setAdapter(adapter);

        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
            }
        });

        btnDelete0 = findViewById(R.id.btnDelete0);
        // Thiết lập sự kiện cho nút Xoá
        btnDelete0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmpoyee();
            }
        });

        btnEdit0 = findViewById(R.id.btnEdit0);
        // Thiết lập sự kiện cho nút Sửa
        btnEdit0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEmployee();
            }
        });

        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd0 = findViewById(R.id.btnAdd0);
        btnAdd0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageEmployeeActivity.this, CreateAccountEmployeeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void deleteEmpoyee() {
        if (selectedPosition != -1) {
            employeeList.remove(selectedPosition);
            adapter.notifyDataSetChanged();
            selectedPosition = -1;
            Toast.makeText(this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng chọn nhân viên", Toast.LENGTH_SHORT).show();
        }
    }

    private void editEmployee() {
        if (selectedPosition != -1) {
            // Hiển thị dialog để nhập thông tin mới cho nhân viên
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sửa nhân viên");

            // Tạo EditText để nhập tên mới
            final EditText input = new EditText(this);
            input.setText(employeeList.get(selectedPosition));
            builder.setView(input);

            // Nút lưu thay đổi
            builder.setPositiveButton("Lưu", (dialog, which) -> {
                String newName = input.getText().toString();
                if (!newName.isEmpty()) {
                    employeeList.set(selectedPosition, newName);
                    adapter.notifyDataSetChanged();
                    selectedPosition = -1;
                    Toast.makeText(this, "Đã sửa nhân viên", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                }
            });

            // Nút hủy
            builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

            builder.show();
        } else {
            Toast.makeText(this, "Vui lòng chọn nhân viên", Toast.LENGTH_SHORT).show();
        }
    }
}