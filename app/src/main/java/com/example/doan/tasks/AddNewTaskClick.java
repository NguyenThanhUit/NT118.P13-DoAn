package com.example.doan.tasks;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewTaskClick {
    private Tasks tasks;
    private Context context;
    private TasksViewModel tasksViewModel;

    public AddNewTaskClick() {
        super();
    }

    public AddNewTaskClick(Tasks tasks, Context context, TasksViewModel tasksViewModel) {
        this.tasks = tasks;
        this.context = context;
        this.tasksViewModel = tasksViewModel;
    }

    public void onAssignDateClicked(View view) {
        String currentDateTime = formatDate(new Date());
        tasks.setTaskAssignedDate(currentDateTime);
        Toast.makeText(context, "Assignment date: " + currentDateTime, Toast.LENGTH_SHORT).show();
    }

    public void onDueDateClicked(View view) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            new TimePickerDialog(context, (timePicker, hour, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                String dueDate = formatDate(calendar.getTime());
                tasks.setTaskDueDate(dueDate);
                Toast.makeText(context, "Mission due date: " + dueDate, Toast.LENGTH_SHORT).show();
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onSubmitBtnClicked(View view) {
        // Trim input values and validate
        if (isEmptyField(tasks.getTaskDecription()) ||
                isEmptyField(tasks.getTaskAssignedDate()) ||
                isEmptyField(tasks.getTaskDueDate()) ||
                isEmptyField(tasks.getEmployeeID())) {
            Toast.makeText(context, "Please fill in all information!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);

            String assignedDateStr = tasks.getTaskAssignedDate().trim();
            String dueDateStr = tasks.getTaskDueDate().trim();

            Log.d("DEBUG", "Assigned Date: [" + assignedDateStr + "]");
            Log.d("DEBUG", "Due Date: [" + dueDateStr + "]");

            Date assignedDate = sdf.parse(assignedDateStr);
            Date dueDate = sdf.parse(dueDateStr);

            if (dueDate != null && assignedDate != null && dueDate.before(assignedDate)) {
                Toast.makeText(context, "Due date must be later than the assigned date!\nAssigned: "
                        + assignedDateStr + ", Due: " + dueDateStr, Toast.LENGTH_SHORT).show();
            } else {
                Tasks newtask = new Tasks(
                        tasks.getTaskDecription(),
                        tasks.getTaskAssignedDate(),
                        tasks.getTaskStatus(),
                        tasks.getTaskDueDate(),
                        tasks.getTaskCompletedDate(),
                        tasks.getTaskNotes(),
                        tasks.getEmployeeID()
                );
                tasksViewModel.addnewTask(newtask);
                Toast.makeText(context, "Add new task successfully", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException e) {
            Log.e("DateError", "Error parsing dates", e);
            Toast.makeText(context, "Invalid date format! Please use dd/MM/yyyy HH:mm.", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to format dates
    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(date);
    }

    // Helper method to check if a field is empty
    private boolean isEmptyField(String field) {
        return TextUtils.isEmpty(field == null ? "" : field.trim());
    }
}
