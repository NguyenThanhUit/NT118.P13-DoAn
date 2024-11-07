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

public class AssignTaskActivity extends AppCompatActivity {

    ListView lvAssign;
    ArrayList<String> assignList;
    ArrayAdapter<String> adapter;
    Button btnDelete, btnEdit, btnAdd;
    ImageButton imgBtnBack;
    int selectedPosition0 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assign_task);

        lvAssign = findViewById(R.id.lvAssign);
        assignList = new ArrayList<>();
        assignList.add("NV01");
        assignList.add("NV02");
        assignList.add("NV03");
        assignList.add("NV04");
        assignList.add("NV05");
        assignList.add("NV06");
        assignList.add("NV07");
        assignList.add("NV08");
        assignList.add("NV09");
        assignList.add("NV10");

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                assignList
        );

        lvAssign.setAdapter(adapter);

        imgBtnBack = findViewById(R.id.imgBtnBack3);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignTaskActivity.this, CreateTaskActivity.class);
                startActivity(intent);
            }
        });

        lvAssign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition0 = position;
            }
        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTask();
            }
        });

    }

    private void deleteTask()
    {
        if(selectedPosition0 != -1)
        {
            assignList.remove(selectedPosition0);
            adapter.notifyDataSetChanged();
            selectedPosition0 = -1;
            Toast.makeText(this, "Đã xóa nhiệm vụ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Vui lòng chọn nhiệm vụ", Toast.LENGTH_SHORT).show();
        }
    }

    private void editTask() {
        if (selectedPosition0 != -1) {
            // Hiển thị dialog để nhập thông tin mới cho nhân viên
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sửa nhiệm vụ");

            // Tạo EditText để nhập tên mới
            final EditText input = new EditText(this);
            input.setText(assignList.get(selectedPosition0));
            builder.setView(input);

            // Nút lưu thay đổi
            builder.setPositiveButton("Lưu", (dialog, which) -> {
                String newName = input.getText().toString();
                if (!newName.isEmpty()) {
                    assignList.set(selectedPosition0, newName);
                    adapter.notifyDataSetChanged();
                    selectedPosition0 = -1;
                    Toast.makeText(this, "Đã sửa nhiệm vụ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                }
            });

            // Nút hủy
            builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

            builder.show();
        } else {
            Toast.makeText(this, "Vui lòng chọn nhiệm vụ", Toast.LENGTH_SHORT).show();
        }
    }
}