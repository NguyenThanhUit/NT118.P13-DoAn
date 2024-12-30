package com.example.doan.ui;

import android.content.Intent;
import android.media.MediaTimestamp;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.databinding.OrderFragmentBinding;
import com.example.doan.goods.AdapterForGoods;
import com.example.doan.goods.AddNewGoodsActivity;
import com.example.doan.goods.Goods;
import com.example.doan.goods.GoodsViewModel;
import com.example.doan.orders.OrderTotal;

import java.util.ArrayList;

public class OrderFragment extends Fragment  {

    private AdapterForGoods myAdapter;
    private GoodsViewModel myViewModel;
    private RecyclerView recyclerView;
    private OrderFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = OrderFragmentBinding.inflate(inflater, container, false);

        myViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);

        recyclerView = binding.rvGoods;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        myAdapter = new AdapterForGoods(new ArrayList<>(), myViewModel);
        recyclerView.setAdapter(myAdapter);

        binding.btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKeyWord = binding.edtOrder.getText().toString().toLowerCase();
                if (!searchKeyWord.isEmpty()) {
                    myViewModel.getAllGoods().observe(getViewLifecycleOwner(), goodsList -> {
                        ArrayList<Goods> filteredGoods = new ArrayList<>();
                        for (Goods goods : goodsList) {
                            if (goods.getGName().toLowerCase().contains(searchKeyWord)) {
                                filteredGoods.add(goods);
                            }
                        }
                        myAdapter.setData(filteredGoods);
                    });
                } else {

                    myViewModel.getAllGoods().observe(getViewLifecycleOwner(), goodsList -> {
                        myAdapter.setData(new ArrayList<>(goodsList));
                    });
                }
            }
        });

        String employeeID;
        if (getArguments() != null) {
            employeeID = getArguments().getString("EMPLOYEE_ID");
        } else {
            employeeID = null;
        }
        Log.d("OrderFragment", "Received EMPLOYEE_ID: " + employeeID);


        myViewModel.getAllGoods().observe(getViewLifecycleOwner(), goodsList -> {
            if (goodsList != null) {
                Log.d("OrderFragment", "Goods list updated with " + goodsList.size() + " items.");
                for (Goods goods : goodsList) {
                    Log.d("OrderFragment", goods.toString());
                }
                myAdapter.setData((ArrayList<Goods>) goodsList);

                boolean hasGoodsWithSufficientQuantity = false;
                for (Goods goods : goodsList) {
                    if (goods.getGOQuantity() > 0) {
                        hasGoodsWithSufficientQuantity = true;
                        break;
                    }
                }


                binding.btnViewOrder.setVisibility(hasGoodsWithSufficientQuantity ? View.VISIBLE : View.GONE);
            } else {
                Log.d("OrderFragment", "Goods list is null.");
                binding.btnViewOrder.setVisibility(View.GONE);
            }

            binding.btnViewOrder.setOnClickListener(view -> {
                Intent i = new Intent(getContext(), OrderTotal.class);
                i.putParcelableArrayListExtra("goodsList", new ArrayList<>(goodsList));
                i.putExtra("EMPLOYEE_ID", employeeID);
                startActivity(i);

            });
        });



        binding.setClickHandles(this);

        return binding.getRoot();
    }

    public void onFABClicked(View view) {
        Intent intent = new Intent(getActivity(), AddNewGoodsActivity.class);
        startActivity(intent);
    }
}