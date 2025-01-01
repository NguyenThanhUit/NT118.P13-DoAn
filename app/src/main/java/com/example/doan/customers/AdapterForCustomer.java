package com.example.doan.customers;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.CustomerListItemBinding;
import com.example.doan.tasks.AdapterForTask;

import java.util.ArrayList;

public class AdapterForCustomer extends RecyclerView.Adapter<AdapterForCustomer.CustomerViewHolder> {
    private ArrayList<Customers> customer;
    private OnItemClickListener listener;

    public AdapterForCustomer(ArrayList<Customers> customer) {
        this.customer = customer;
    }

    public interface OnItemClickListener {
        void onItemClick(Customers customers);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        holder.bind(currentCustomer, listener);
    }

    @Override
    public int getItemCount() {
        return (customer != null) ? customer.size() : 0;
    }

    public void setCustomer(ArrayList<Customers> customer) {
        this.customer = customer;
        notifyDataSetChanged();
    }

    public ArrayList<Customers> getCustomerList() {
        return customer;
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        private final CustomerListItemBinding binding;

        public CustomerViewHolder(CustomerListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Customers customers, OnItemClickListener listener) {
            binding.setCustomer(customers);
            binding.executePendingBindings();

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(customers);
                }
            });
        }
    }
}
