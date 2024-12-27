package com.example.doan.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ItemTaskBinding;

import java.util.ArrayList;

public class AdapterForTask extends RecyclerView.Adapter<AdapterForTask.TaskViewHolder> {
    private ArrayList<Tasks> tasks;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Tasks task);
    }

    public AdapterForTask(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }


    //Xu li su kien click vao mot task trong recycleView
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        holder.bind(currentTask, listener);
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

        public void bind(Tasks task, OnItemClickListener listener) {
            binding.setTask(task);
            binding.executePendingBindings();


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(task);
                    }
                }
            });
        }
    }

    public ArrayList<Tasks> getTasksList() {
        return tasks;
    }
}
