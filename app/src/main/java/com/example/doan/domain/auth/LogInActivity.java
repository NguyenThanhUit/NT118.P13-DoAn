package com.example.doan.domain.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.doan.MainActivity;
import com.example.doan.MainActivityForSaleEmployee;
import com.example.doan.R;
import com.example.doan.domain.employee.Employees;
import com.example.doan.database.InfoDatabase;
import com.example.doan.tasks.Tasks;
import com.example.doan.ui.TasksLookUP;

import java.util.ArrayList;
import java.util.List;

public class LogInActivity extends AppCompatActivity {

    private EditText etusername, etpassword;
    private InfoDatabase database;

    private Tasks tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        etusername = findViewById(R.id.tie_username);
        etpassword = findViewById(R.id.tie_password);
        Button loginButton = findViewById(R.id.btn_login);

        database = InfoDatabase.getInstance(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();

                if ("admin".equals(username)) {
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    database.getEDAO().getEmployeesByUsername(username).observe(LogInActivity.this, new Observer<List<Employees>>() {
                        @Override
                        public void onChanged(List<Employees> employees) {
                            if (employees != null && !employees.isEmpty()) {
                                Employees employee = employees.get(0);
                                if (password.equals(employee.getPassword())) {
                                    if ("Sales agent".equals(employee.getPosition())) {
                                        database.getTDAO().getTasksForEmployee(employee.getEid()).observe(LogInActivity.this, new Observer<List<Tasks>>() {
                                            @Override
                                            public void onChanged(List<Tasks> tasks) {

                                                ArrayList<TasksLookUP> tasksLookUPList = convertTasksToTasksLookUP(tasks);


                                                Intent intent = new Intent(LogInActivity.this, MainActivityForSaleEmployee.class);
                                                intent.putExtra("EMPLOYEE_ID", employee.getEid());
                                                intent.putExtra("USER_NAME", employee.getName());
                                                intent.putParcelableArrayListExtra("TASKS", tasksLookUPList);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    } else {
                                        database.getTDAO().getTasksForEmployee(employee.getEid()).observe(LogInActivity.this, new Observer<List<Tasks>>() {
                                            @Override
                                            public void onChanged(List<Tasks> tasks) {

                                                ArrayList<TasksLookUP> tasksLookUPList = convertTasksToTasksLookUP(tasks);


                                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                                intent.putExtra("USER_NAME", employee.getName());
                                                intent.putParcelableArrayListExtra("TASKS", tasksLookUPList);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(LogInActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LogInActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    private ArrayList<TasksLookUP> convertTasksToTasksLookUP(List<Tasks> tasks) {
        ArrayList<TasksLookUP> tasksLookUPList = new ArrayList<>();

        if (tasks != null && !tasks.isEmpty()) {
            for (Tasks task : tasks) {

                String taskName = task.getTaskID();
                String taskDescription = task.getTaskDecription() != null ? task.getTaskDecription() : "No description available";
                String taskAssignedDate = task.getTaskAssignedDate() != null ? task.getTaskAssignedDate() : "Not assigned";
                String taskStatus = task.getTaskStatus() != null ? task.getTaskStatus() : "Chưa hoàn thành";
                String taskDueDate = task.getTaskDueDate() != null ? task.getTaskDueDate() : "No due date";
                String taskCompletedDate = task.getTaskCompletedDate() != null ? task.getTaskCompletedDate() : "Not completed";
                String taskNotes = task.getTaskNotes() != null ? task.getTaskNotes() : "No notes available";


                TasksLookUP tasksLookUP = new TasksLookUP(
                        taskName,
                        taskAssignedDate,
                        taskStatus,
                        taskDescription,
                        taskDueDate,
                        taskCompletedDate,
                        taskNotes
                );

                Log.d("TaskConversion", "Task Added: " + taskName + " | Assigned Date: " + taskAssignedDate + " | Status: " + taskStatus);

                tasksLookUPList.add(tasksLookUP);
            }
        }
        return tasksLookUPList;
    }
}
