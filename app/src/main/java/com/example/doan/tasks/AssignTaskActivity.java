package com.example.doan.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ActivityAssignTaskBinding;
import com.example.doan.domain.employee.Employees;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskActivity extends AppCompatActivity {
    private AdapterForTask myAdapter;
    private TasksViewModel myViewModel;
    private ActivityAssignTaskBinding binding;
    private AddNewTaskClick addNewTaskClick;
    Button btnDelete, btnEdit, btnAdd;
    ImageButton imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assign_task);



        RecyclerView rvAssign = findViewById(R.id.lvAssign);
        rvAssign.setLayoutManager(new LinearLayoutManager(this)); // Sử dụng LinearLayout
        rvAssign.setHasFixedSize(true);

        myAdapter = new AdapterForTask(new ArrayList<>());
        rvAssign.setAdapter(myAdapter);

        myViewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        addNewTaskClick = new AddNewTaskClick(null, this, myViewModel);

        LiveData<List<Tasks>> tasksLiveData = myViewModel.getAlltasks();
        tasksLiveData.observe(this, new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> tasks) {
                if (tasks != null) {
                    myAdapter.setTasksLists(new ArrayList<>(tasks));
                } else {
                    myAdapter.setTasksLists(new ArrayList<>());
                }
            }
        });



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
                Intent intent = new Intent(AssignTaskActivity.this, AddnewTaskActivity.class);
                startActivity(intent);
            }
        });

    }


}