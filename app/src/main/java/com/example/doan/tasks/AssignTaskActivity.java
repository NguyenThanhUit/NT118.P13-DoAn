package com.example.doan.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
import com.example.doan.ui.AdminHomeFragment;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskActivity extends AppCompatActivity {
    private AdapterForTask myAdapter;
    private TasksViewModel myViewModel;
    private ActivityAssignTaskBinding binding;
    private AddNewTaskClick addNewTaskClick;
    ImageButton imgBtnBack, btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assign_task);



        RecyclerView rvAssign = findViewById(R.id.lvAssign);
        rvAssign.setLayoutManager(new LinearLayoutManager(this));
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


        myAdapter.setOnItemClickListener(new AdapterForTask.OnItemClickListener() {
            @Override
            public void onItemClick(Tasks task) {
                showTaskInfo(task);
            }
        });


        btnBack = findViewById(R.id.ic_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AssignTaskActivity.this, AdminHomeFragment.class);
                startActivity(i);
            }
        });


        btnAdd = findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignTaskActivity.this, AddnewTaskActivity.class);
                startActivity(intent);
            }
        });



    }
    public void showTaskInfo(Tasks task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.task_infor, null);


        TextView tvTaskId = dialogView.findViewById(R.id.tv_task_id);
        TextView tvTaskTitle = dialogView.findViewById(R.id.tv_task_title);
        TextView tvTaskDirection = dialogView.findViewById(R.id.tv_task_direction);
        TextView tvTaskStart = dialogView.findViewById(R.id.tv_task_start);
        TextView tvTaskEnd = dialogView.findViewById(R.id.tv_task_end);
        TextView tvTaskDescription = dialogView.findViewById(R.id.tv_task_description);


        tvTaskId.setText(task.getTaskID());
        tvTaskTitle.setText(task.getTaskNotes());
        tvTaskDirection.setText(task.getTaskDecription());
        tvTaskStart.setText(task.getTaskAssignedDate());
        tvTaskEnd.setText(task.getTaskDueDate());
//        tvTaskDescription.setText(task.getTaskDescription());


        builder.setView(dialogView)
                .setPositiveButton("Close", (dialog, which) -> dialog.dismiss())
                .setCancelable(true);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}