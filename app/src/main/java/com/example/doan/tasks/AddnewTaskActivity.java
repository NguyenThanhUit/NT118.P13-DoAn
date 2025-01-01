package com.example.doan.tasks;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;
import com.example.doan.database.InfoDatabase;
import com.example.doan.databinding.CreateTaskBinding;
import com.example.doan.domain.employee.Employees;

public class AddnewTaskActivity extends AppCompatActivity {

    private CreateTaskBinding createTaskBinding;
    private AddNewTaskClick handlers;
    private Tasks tasks;
    private TasksViewModel tasksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_task);

        tasksViewModel = new ViewModelProvider(this).get(TasksViewModel.class);

        tasks = new Tasks();

        createTaskBinding = DataBindingUtil.setContentView(this, R.layout.create_task);
        handlers = new AddNewTaskClick(tasks, this, tasksViewModel);
        createTaskBinding.setTask(tasks);
        createTaskBinding.setClickHandler(handlers);

        InfoDatabase db = InfoDatabase.getInstance(this);

        Spinner spinnerEmployeeList = findViewById(R.id.spinnerEmployeeList);
        spinnerEmployeeList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Employees selectedEmployee = (Employees) parent.getItemAtPosition(position);
                tasks.setEmployeeID(selectedEmployee.getEid()); // Lấy employee_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        db.getEDAO().getallEID().observe(this, employeeDisplayList -> {

            ArrayAdapter<Employees> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    employeeDisplayList
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEmployeeList.setAdapter(adapter);
        });


        ImageButton imgBtnBack4 = findViewById(R.id.imgBtnBack4);
        imgBtnBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}