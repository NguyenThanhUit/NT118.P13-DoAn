package com.example.doan.order;

import android.net.Uri;
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


public class AdapterForGoods extends RecyclerView.Adapter<AdapterForGoods.GoodsViewHolder> {
    private ArrayList<?> goodsList;
    private OnGoodsItemClickListener listener;
    private GoodsViewModel goodsViewModel;

    public AdapterForGoods(ArrayList<Goods> goodsList, GoodsViewModel goodsViewModel) {
        this.goodsList = goodsList != null ? goodsList : new ArrayList<>();
        this.goodsViewModel = goodsViewModel;
        this.listener = listener;
    }

    public interface OnGoodsItemClickListener {
        void onAddToItemOrderClick(Goods goods);
        void onRemoveItemOrderClick(Goods goods, int newCount);
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
        Goods currentGoods = (Goods) goodsList.get(position);
        holder.binding.setGoods(currentGoods);
        ImageView imageView = holder.binding.imgGoods;

        if (currentGoods.getGImage() != null && !currentGoods.getGImage().isEmpty()) {
            imageView.setImageURI(Uri.parse(currentGoods.getGImage()));
        } else {
            imageView.setImageResource(R.drawable.custom_icon);
        }

        holder.binding.executePendingBindings();


        int count = currentGoods.getGOQuantity();

        holder.binding.quantityText.setText(String.valueOf(count));
        holder.binding.btnDecrease.setVisibility(count > 0 ? View.VISIBLE : View.GONE);


        holder.binding.btnIncrease.setOnClickListener(v -> {
            int newCount = count + 1;
            holder.binding.quantityText.setText(String.valueOf(newCount));
            currentGoods.setGOQuantity(newCount);


            if (goodsViewModel != null) {
                goodsViewModel.updateGoodsQuantity(currentGoods.getGID(), newCount);
            }

            if (listener != null) {
                listener.onAddToItemOrderClick(currentGoods);
            }
            notifyItemChanged(position);
        });


        holder.binding.btnDecrease.setOnClickListener(v -> {
            if (count > 0) {
                int newCount = count - 1;
                holder.binding.quantityText.setText(String.valueOf(newCount));
                currentGoods.setGOQuantity(newCount);


                if (goodsViewModel != null) {
                    goodsViewModel.updateGoodsQuantity(currentGoods.getGID(), newCount);
                }

                if (listener != null) {
                    listener.onRemoveItemOrderClick(currentGoods, newCount);
                }
                notifyItemChanged(position);
            }

            holder.binding.btnDecrease.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
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

