package com.example.doan.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class TasksLookUP implements Parcelable {
    private String taskName;
    private String taskAssignedDate;
    private String taskStatus;


    public TasksLookUP(String taskName, String taskAssignedDate, String taskStatus) {
        this.taskName = taskName;
        this.taskAssignedDate = taskAssignedDate;
        this.taskStatus = taskStatus;
    }


    public String getTaskName() {
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


    protected TasksLookUP(Parcel in) {
        taskName = in.readString();
        taskAssignedDate = in.readString();
        taskStatus = in.readString();
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
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
