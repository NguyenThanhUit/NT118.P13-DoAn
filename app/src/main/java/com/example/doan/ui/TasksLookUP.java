package com.example.doan.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class TasksLookUP implements Parcelable {
    private String taskName;
    private String taskAssignedDate;
    private String taskStatus;
    private String taskDecription;
    private String taskDueDate;
    private String taskCompletedDate;
    private String taskNotes;

    public TasksLookUP(String taskName, String taskAssignedDate, String taskStatus,
                       String taskDecription, String taskDueDate, String taskCompletedDate, String taskNotes) {
        this.taskName = taskName;
        this.taskAssignedDate = taskAssignedDate;
        this.taskStatus = taskStatus;
        this.taskDecription = taskDecription;
        this.taskDueDate = taskDueDate;
        this.taskCompletedDate = taskCompletedDate;
        this.taskNotes = taskNotes;
    }

    public  String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskAssignedDate() {
        return taskAssignedDate;
    }

    public void setTaskAssignedDate(String taskAssignedDate) {
        this.taskAssignedDate = taskAssignedDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDecription() {
        return taskDecription;
    }

    public void setTaskDecription(String taskDecription) {
        this.taskDecription = taskDecription;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskCompletedDate() {
        return taskCompletedDate;
    }

    public void setTaskCompletedDate(String taskCompletedDate) {
        this.taskCompletedDate = taskCompletedDate;
    }

    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    protected TasksLookUP(Parcel in) {
        taskName = in.readString();
        taskAssignedDate = in.readString();
        taskStatus = in.readString();
        taskDecription = in.readString();
        taskDueDate = in.readString();
        taskCompletedDate = in.readString();
        taskNotes = in.readString();
    }

    public static final Creator<TasksLookUP> CREATOR = new Creator<TasksLookUP>() {
        @Override
        public TasksLookUP createFromParcel(Parcel in) {
            return new TasksLookUP(in);
        }

        @Override
        public TasksLookUP[] newArray(int size) {
            return new TasksLookUP[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskName);
        dest.writeString(taskAssignedDate);
        dest.writeString(taskStatus);
        dest.writeString(taskDecription);
        dest.writeString(taskDueDate);
        dest.writeString(taskCompletedDate);
        dest.writeString(taskNotes);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
