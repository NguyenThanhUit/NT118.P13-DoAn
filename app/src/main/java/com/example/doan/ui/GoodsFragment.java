package com.example.doan.ui;

import android.os.Bundle;
import android.util.Log;
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
    private List<Orders> allOrders = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
                    allOrders = orders;
                    myAdapter.setOrders((ArrayList<Orders>) orders);
                    myAdapter.notifyDataSetChanged();
                } else {
                    Log.d("GoodsFragment", "No orders found");
                    myAdapter.setOrders(new ArrayList<>());
                }
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKeyword = binding.etSearch.getText().toString().trim();

                if (!searchKeyword.isEmpty()) {
                    ArrayList<Orders> filterOrders = new ArrayList<>();

                    for (Orders orders : allOrders) {
                        if (orders.getOID().toLowerCase().contains(searchKeyword.toLowerCase())) {
                            filterOrders.add(orders);
                        }
                    }
                    myAdapter.setOrders(filterOrders);
                    myAdapter.notifyDataSetChanged();
                } else {

                    myAdapter.setOrders((ArrayList<Orders>) allOrders);
                    myAdapter.notifyDataSetChanged();
                }
            }
        });

        return binding.getRoot();
    }
}
