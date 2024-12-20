package com.example.doan.customers;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.CustomerListItemBinding;
import java.util.ArrayList;

public class AdapterForCustomer extends RecyclerView.Adapter<AdapterForCustomer.CustomerViewHolder> {
    private ArrayList<Customers> customer;


    public AdapterForCustomer(ArrayList<Customers> customer) {
        this.customer = customer;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout bằng DataBinding
        CustomerListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.customer_list_item,
                parent,
                false
        );
        return new CustomerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customers currentCustomer = customer.get(position);
        ImageView indicatorSquare = holder.binding.getRoot().findViewById(R.id.indicatorSquare);



        if ("Mới".equalsIgnoreCase(currentCustomer.getCategory())) {
            indicatorSquare.setBackgroundColor(Color.GRAY);
        } else if ("Chưa tiếp cận".equalsIgnoreCase(currentCustomer.getCategory())) {
            indicatorSquare.setBackgroundColor(Color.YELLOW);
        } else if ("Tiếp cận".equalsIgnoreCase(currentCustomer.getCategory())) {
            indicatorSquare.setBackgroundColor(Color.BLUE);
        } else if ("Nóng".equalsIgnoreCase(currentCustomer.getCategory())) {
            indicatorSquare.setBackgroundColor(Color.RED);
        } else if ("Tiềm năng".equalsIgnoreCase(currentCustomer.getCategory())) {
            indicatorSquare.setBackgroundColor(Color.CYAN);
        }

        holder.binding.setCustomer(currentCustomer);
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return (customer != null) ? customer.size() : 0;
    }

    public void setCustomer(ArrayList<Customers> customer) {
        this.customer = customer;
        notifyDataSetChanged();
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        private final CustomerListItemBinding binding;

        public CustomerViewHolder(CustomerListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public ArrayList<Customers> getCustomerList() {
        return customer;
    }

}
