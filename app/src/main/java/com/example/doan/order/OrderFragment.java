package com.example.doan.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.doan.R;
import com.example.doan.databinding.OrderFragmentBinding;
import java.util.ArrayList;

public class OrderFragment extends Fragment  {

    private AdapterForGoods myAdapter;
    private GoodsViewModel myViewModel;
    private RecyclerView recyclerView;
    private OrderFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = OrderFragmentBinding.inflate(inflater, container, false);

        recyclerView = binding.rvGoods;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        myAdapter = new AdapterForGoods(new ArrayList<>(), myViewModel);
        recyclerView.setAdapter(myAdapter);

        myViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);

        myViewModel.getAllGoods().observe(getViewLifecycleOwner(), goodsList -> {
            if (goodsList != null) {
                Log.d("OrderFragment", "Goods list updated with " + goodsList.size() + " items.");
                for (Goods goods : goodsList) {
                    Log.d("OrderFragment", goods.toString());
                }
                myAdapter.setData((ArrayList<Goods>) goodsList);
            } else {
                Log.d("OrderFragment", "Goods list is null.");
            }
        });


        binding.btnViewOrder.setOnClickListener(view -> {

        });


        binding.setClickHandles(this);

        return binding.getRoot();
    }

    public void onFABClicked(View view) {
        Intent intent = new Intent(getActivity(), AddNewGoodsActivity.class);
        startActivity(intent);
    }
}