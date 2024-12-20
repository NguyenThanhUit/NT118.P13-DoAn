package com.example.doan.tasks;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewTaskClick {
    private Tasks tasks;
    private Context context;
    private TasksViewModel tasksViewModel;

    public AddNewTaskClick(){super();}

    public AddNewTaskClick(Tasks tasks, Context context, TasksViewModel tasksViewModel) {
        this.tasks = tasks;
        this.context = context;
        this.tasksViewModel = tasksViewModel;
    }
    public void onAssignDateClicked(View view) {
        String currentDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());
        tasks.setTaskAssignedDate(currentDateTime);
        Toast.makeText(context, "Ngày giao: " + currentDateTime, Toast.LENGTH_SHORT).show();
    }

    public void onDueDateClicked(View view) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, (datePicker, year, month, day) -> {
            // Khi người dùng chọn ngày
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            new TimePickerDialog(context, (timePicker, hour, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                String dueDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(calendar.getTime());
                tasks.setTaskDueDate(dueDate);
                Toast.makeText(context, "Ngày hết hạn: " + dueDate, Toast.LENGTH_SHORT).show();
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    

    public void onSubmitBtnClicked(View view) {
        Log.d("DEBUG", "TaskID: " + tasks.getTaskID());
        Log.d("DEBUG", "Description: " + tasks.getTaskDecription());
        Log.d("DEBUG", "Assign Date: " + tasks.getTaskAssignedDate());
        Log.d("DEBUG", "Due Date: " + tasks.getTaskDueDate());
        Log.d("DEBUG", "EmployeeID: " + tasks.getEmployeeID());
        if (TextUtils.isEmpty(tasks.getTaskID()) ||
                TextUtils.isEmpty(tasks.getTaskDecription()) ||
                TextUtils.isEmpty(tasks.getTaskAssignedDate()) ||
                TextUtils.isEmpty(tasks.getTaskDueDate()) ||
                TextUtils.isEmpty(tasks.getEmployeeID())) {
            Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }else{
            Tasks newtask  = new Tasks(
                    tasks.getTaskID(),
                    tasks.getTaskDecription(),
                    tasks.getTaskAssignedDate(),
                    tasks.getTaskStatus(),
                    tasks.getTaskDueDate(),
                    tasks.getTaskCompletedDate(),
                    tasks.getTaskNotes(),
                    tasks.getEmployeeID()
            );
            tasksViewModel.addnewTask(newtask);
            Toast.makeText(context, "Thêm nhiệm vụ thành công", Toast.LENGTH_SHORT).show();
        }
    }



}
