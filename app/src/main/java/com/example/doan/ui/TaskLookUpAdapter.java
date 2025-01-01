package com.example.doan.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;

import java.util.ArrayList;

public class TaskLookUpAdapter extends RecyclerView.Adapter<TaskLookUpAdapter.TaskViewHolder> {

    private final Context context;
    private final ArrayList<com.example.doan.ui.TasksLookUP> taskList;
    private OnItemClickListener listener;

    public TaskLookUpAdapter(Context context, ArrayList<com.example.doan.ui.TasksLookUP> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    public interface OnItemClickListener {
        void onItemClick(com.example.doan.ui.TasksLookUP task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lookup_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        com.example.doan.ui.TasksLookUP task = taskList.get(position);

        holder.taskID.setText("Task: " + task.getTaskName());
        holder.taskAssignedDate.setText("Assigned Date: " + task.getTaskAssignedDate());
        holder.taskStatus.setText("Status: " + task.getTaskStatus());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskID;
        TextView taskAssignedDate;
        TextView taskStatus;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskID = itemView.findViewById(R.id.tvTaskID);
            taskAssignedDate = itemView.findViewById(R.id.tvTaskAssignedDate);
            taskStatus = itemView.findViewById(R.id.tvTaskStatus);
        }
    }
}
