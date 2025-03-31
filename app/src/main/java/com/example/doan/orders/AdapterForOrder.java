package com.example.doan.orders;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ItemForEachListGoodsBinding;

import java.util.ArrayList;

public class AdapterForOrder extends RecyclerView.Adapter<AdapterForOrder.OrderViewHolder> {
    private ArrayList<Orders> orders;
    private OrdersViewModel myViewModel;

    public AdapterForOrder(ArrayList<Orders> orders, OrdersViewModel myViewModel) {
        this.orders = orders;
        this.myViewModel = myViewModel;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemForEachListGoodsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_for_each_list_goods,
                parent,
                false
        );
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Orders currentOrder = orders.get(position);
        holder.binding.setOrders(currentOrder);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return orders != null ? orders.size() : 0;
    }

    public void setOrders(ArrayList<Orders> newOrders) {
        orders = new ArrayList<>(newOrders);
        notifyDataSetChanged();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        final ItemForEachListGoodsBinding binding;

        public OrderViewHolder(@NonNull ItemForEachListGoodsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
