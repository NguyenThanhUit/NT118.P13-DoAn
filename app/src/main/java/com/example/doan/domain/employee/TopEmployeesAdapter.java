package com.example.doan.domain.employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import java.util.List;

public class TopEmployeesAdapter extends RecyclerView.Adapter<TopEmployeesAdapter.ViewHolder> {

    private List<EmployeeDetails> employees;

    public TopEmployeesAdapter(List<EmployeeDetails> employees) {
        this.employees = employees;
    }

    public void setEmployees(List<EmployeeDetails> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmployeeDetails employee = employees.get(position);
        holder.name.setText(employee.employeeName);
        holder.phone.setText(employee.employeePhone);
        holder.email.setText(employee.employeeEmail);
        holder.totalOrder.setText(formatCurrency(employee.totalOrder)); // Định dạng số tiền
    }

    @Override
    public int getItemCount() {
        return employees != null ? employees.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, email, totalOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employee_name);
            phone = itemView.findViewById(R.id.employee_phone);
            email = itemView.findViewById(R.id.employee_email);
            totalOrder = itemView.findViewById(R.id.employee_total_order);
        }
    }

    public static String formatCurrency(float amount) {
        if (amount >= 1_000_000_000) {
            return String.format("%.1f", amount / 1_000_000_000).replace(".0", "") + "t"; // Tỷ
        } else if (amount >= 1_000_000) {
            return String.format("%.1f", amount / 1_000_000).replace(".0", "") + "tr"; // Triệu
        } else if (amount >= 1_000) {
            return String.format("%.1f", amount / 1_000).replace(".0", "") + "k"; // Nghìn
        } else {
            return String.valueOf((int) amount); // Không định dạng nếu < 1,000
        }
    }
}