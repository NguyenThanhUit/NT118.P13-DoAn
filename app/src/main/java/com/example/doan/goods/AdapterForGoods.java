package com.example.doan.goods;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ItemGoodsBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterForGoods extends RecyclerView.Adapter<AdapterForGoods.GoodsViewHolder> {
    private List<Goods> goodsList;
    private GoodsViewModel goodsViewModel;

    public AdapterForGoods(ArrayList<Goods> goodsList, GoodsViewModel goodsViewModel) {
        this.goodsList = goodsList != null ? goodsList : new ArrayList<>();
        this.goodsViewModel = goodsViewModel;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGoodsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_goods,
                parent,
                false
        );
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods currentGoods = goodsList.get(position);
        holder.binding.setGoods(currentGoods);


        ImageView imageView = holder.binding.imgGoods;
        if (currentGoods.getGImage() != null && !currentGoods.getGImage().isEmpty()) {
            imageView.setImageURI(Uri.parse(currentGoods.getGImage()));
        } else {
            imageView.setImageResource(R.drawable.custom_icon);
        }

        int count = currentGoods.getGOQuantity();
        holder.binding.btnDecrease.setVisibility(count > 0 ? View.VISIBLE : View.GONE);


        holder.binding.executePendingBindings();


        Log.d("GoodsAdapter", "Before increase, GOQuantity: " + currentGoods.getGOQuantity());

        AddRemoveNewQuantityForEachItem addremoveNewQuantityForEachItem = new AddRemoveNewQuantityForEachItem(
                currentGoods,
                holder.itemView.getContext(),
                goodsViewModel
        );

        holder.binding.btnIncrease.setOnClickListener(view -> {

            int newQuantity = currentGoods.getGOQuantity() + 1;
            currentGoods.setGOQuantity(newQuantity);


            Log.d("GoodsAdapter", "After increase, GOQuantity: " + newQuantity);


            holder.binding.quantityText.setText(String.valueOf(newQuantity));


            addremoveNewQuantityForEachItem.onUpdateBtnClicked(view);
            notifyItemChanged(position);
        });
        holder.binding.btnDecrease.setOnClickListener(view ->{
            int newQuantity = currentGoods.getGOQuantity() - 1;
            currentGoods.setGOQuantity(newQuantity);
            holder.binding.quantityText.setText(String.valueOf(newQuantity));
            addremoveNewQuantityForEachItem.onUpdateBtnClicked(view);
            notifyItemChanged(position);
        });



    }


    @Override
    public int getItemCount() {
        return (goodsList != null) ? goodsList.size() : 0;
    }

    public void setData(ArrayList<Goods> newGoodsList) {
        if (newGoodsList != null) {
            goodsList = new ArrayList<>(newGoodsList);
            notifyDataSetChanged();
        }
    }

    public static class GoodsViewHolder extends RecyclerView.ViewHolder {
        private final ItemGoodsBinding binding;

        public GoodsViewHolder(ItemGoodsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

