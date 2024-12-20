package com.example.doan.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doan.R;

import java.util.ArrayList;

public class TaskLookUpAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<TasksLookUP> taskList;

    public TaskLookUpAdapter(Context context, ArrayList<TasksLookUP> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_lookup_task, parent, false);
            holder = new ViewHolder();
            holder.taskID = convertView.findViewById(R.id.tvTaskID);
            holder.taskAssignedDate = convertView.findViewById(R.id.tvTaskAssignedDate);
            holder.taskStatus = convertView.findViewById(R.id.tvTaskStatus);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TasksLookUP task = taskList.get(position);

        Log.d("TaskLookUpAdapter", "Task Name: " + task.getTaskName() + " Assigned Date: " + task.getTaskAssignedDate() + " Status: " + task.getTaskStatus());
        Log.d("TaskLookUpAdapter", "Data Size: " + taskList.size());
        for (TasksLookUP taska : taskList) {
            Log.d("TaskLookUpAdapter", "Task: " + task.getTaskName());
        }

        holder.taskID.setText("Task: " + task.getTaskName());
        holder.taskAssignedDate.setText("Assigned Date: " + task.getTaskAssignedDate());
        holder.taskStatus.setText("Status: " + task.getTaskStatus());

        return convertView;
    }


    private static class ViewHolder {
        TextView taskID;
        TextView taskAssignedDate;
        TextView taskStatus;
    }
}
