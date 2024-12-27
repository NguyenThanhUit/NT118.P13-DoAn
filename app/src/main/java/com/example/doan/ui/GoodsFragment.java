package com.example.doan.ui;

import android.os.Bundle;
import android.util.Log; // Import Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.databinding.GoodsFragmentBinding;
import com.example.doan.orders.AdapterForOrder;
import com.example.doan.orders.Orders;
import com.example.doan.orders.OrdersViewModel;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends Fragment {
    private GoodsFragmentBinding binding;
    private OrdersViewModel myViewModel;
    private RecyclerView recyclerView;
    private AdapterForOrder myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = GoodsFragmentBinding.inflate(inflater, container, false);
        myViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);

        recyclerView = binding.recyclerViewOrders;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        myAdapter = new AdapterForOrder(new ArrayList<>(), myViewModel);
        recyclerView.setAdapter(myAdapter);

        LiveData<List<Orders>> ordersLiveData = myViewModel.getAllOrders();
        ordersLiveData.observe(getViewLifecycleOwner(), new Observer<List<Orders>>() {
            @Override
            public void onChanged(List<Orders> orders) {
                Log.d("GoodsFragment", "onChanged: Orders list updated");
                if (orders != null) {
                    Log.d("GoodsFragment", "Orders size: " + orders.size());
                    // Log details of each order for debugging
                    for (Orders order : orders) {
                        Log.d("GoodsFragment", "Order ID: " + order.getOID() + ", Price: " + order.getEmployeeID());
                    }
                    myAdapter.setOrders((ArrayList<Orders>) orders);
                    myAdapter.notifyDataSetChanged();
                } else {
                    Log.d("GoodsFragment", "No orders found");
                    myAdapter.setOrders(new ArrayList<>());
                }
            }
        });

        return binding.getRoot();
    }
}
