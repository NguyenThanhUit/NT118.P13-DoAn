package com.example.doan.tasks;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ItemTaskBinding;
import com.example.doan.databinding.TaskLookupBinding;

import java.util.ArrayList;

public class AdapterForTask extends RecyclerView.Adapter<AdapterForTask.TaskViewHolder> {
    private ArrayList<Tasks> tasks;

    public AdapterForTask(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaskBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_task,
                parent,
                false
        );
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Tasks currentTask = tasks.get(position);
        holder.binding.setTask(currentTask);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    public void setTasksLists(ArrayList<Tasks> newTasks) {
        this.tasks = newTasks;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        final ItemTaskBinding binding;

        public TaskViewHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public ArrayList<Tasks> getTasksList() {
        return tasks;
    }
}
