package com.example.doan.tasks;


import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.doan.BR;
import com.example.doan.domain.employee.Employees;

@Entity(
        tableName = "tasks_information",
        foreignKeys = @ForeignKey(
                entity = Employees.class,
                parentColumns = "employee_id",
                childColumns = "employee_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Tasks extends BaseObservable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    private String taskID;

    @ColumnInfo(name = "task_description")
    private String taskDecription;

    @ColumnInfo(name = "task_status")
    private String taskStatus ;

    @ColumnInfo(name = "task_assigneddate")
    private String taskAssignedDate;

    @ColumnInfo(name = "task_dueDate")
    private String taskDueDate;

    @ColumnInfo(name = "task_completedDate")
    private String taskCompletedDate;

    @ColumnInfo(name = "task_notes")
    private String taskNotes;

    @ColumnInfo(name = "employee_id")
    private String employeeID;

    public Tasks( String taskDecription, String taskAssignedDate, String taskStatus, String taskDueDate, String taskCompletedDate, String taskNotes, String employeeID) {
        this.taskDecription = taskDecription;
        this.taskAssignedDate = taskAssignedDate;
        this.taskStatus = (taskStatus == null || taskStatus.isEmpty()) ? "Chưa hoàn thành" : taskStatus;
        this.taskDueDate = taskDueDate;
        this.taskCompletedDate = taskCompletedDate;
        this.taskNotes = taskNotes;
        this.employeeID = employeeID;
    }

    public Tasks() {
    }

    @Bindable
    @NonNull
    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(@NonNull String taskID) {
        this.taskID = taskID;
        notifyPropertyChanged(BR.taskID);
    }

    @Bindable
    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        notifyPropertyChanged(BR.taskStatus);
    }

    @Bindable
    public String getTaskDecription() {
        return taskDecription;
    }

    public void setTaskDecription(String taskDecription) {
        this.taskDecription = taskDecription;
        notifyPropertyChanged(BR.taskDecription);
    }

    @Bindable
    public String getTaskAssignedDate() {
        return taskAssignedDate;
    }

    public void setTaskAssignedDate(String taskAssignedDate) {
        this.taskAssignedDate = taskAssignedDate;
        notifyPropertyChanged(BR.taskAssignedDate);
    }

    @Bindable
    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate) {
        this.taskDueDate = taskDueDate;
        notifyPropertyChanged(BR.taskDueDate);
    }

    @Bindable
    public String getTaskCompletedDate() {
        return taskCompletedDate;
    }

    public void setTaskCompletedDate(String taskCompletedDate) {
        this.taskCompletedDate = taskCompletedDate;
        notifyPropertyChanged(BR.taskCompletedDate);
    }

    @Bindable
    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
        notifyPropertyChanged(BR.taskNotes);
    }

    @Bindable
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
        notifyPropertyChanged(BR.employeeID);
    }
}