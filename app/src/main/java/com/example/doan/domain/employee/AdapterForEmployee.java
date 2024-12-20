package com.example.doan.domain.employee;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ItemEmployeeBinding;

import java.util.ArrayList;

public class AdapterForEmployee extends RecyclerView.Adapter<AdapterForEmployee.EmployeeViewHolder> {

    private ArrayList<Employees> employeeList;

    public AdapterForEmployee(ArrayList<Employees> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_employee,
                parent,
                false
        );
        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employees currentEmployee = employeeList.get(position);
        holder.binding.setEmployee(currentEmployee); // Liên kết với biến 'employee' trong layout
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return (employeeList != null) ? employeeList.size() : 0;
    }
    public void setEmployeeLists(ArrayList<Employees> employeeLists) {
        this.employeeList = employeeLists;
        notifyDataSetChanged();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private final ItemEmployeeBinding binding;

        public EmployeeViewHolder(ItemEmployeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public ArrayList<Employees> getEmployeeLists() {
        return employeeList;
    }
}
