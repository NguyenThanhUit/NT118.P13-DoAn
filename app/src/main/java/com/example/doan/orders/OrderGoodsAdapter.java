package com.example.doan.orders;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.goods.Goods;

import java.util.ArrayList;

public class OrderGoodsAdapter extends RecyclerView.Adapter<OrderGoodsAdapter.ViewHolder> {
    private ArrayList<Goods> goodsList;
    private Context context;

    public OrderGoodsAdapter(Context context, ArrayList<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_infor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Goods goods = goodsList.get(position);
        Log.d("OrderGoodsAdapter", "Binding goods: " + goods.getGName() + ", Price: " + goods.getGPrice());
        holder.tvGoodsName.setText(goods.getGName());
        holder.tvGoodsName.setText(goods.getGName());
        holder.tvGoodsID.setText(goods.getGID());
        holder.tvGoodsPrice.setText(goods.getGPrice());
        holder.tvGoodsQuantity.setText(String.valueOf(goods.getGOQuantity()));
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGoodsName, tvGoodsID, tvGoodsPrice, tvGoodsQuantity;
        ImageView ivGoodsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvGoodsID = itemView.findViewById(R.id.tv_goods_id);
            tvGoodsPrice = itemView.findViewById(R.id.tv_goods_price);
            tvGoodsQuantity = itemView.findViewById(R.id.tv_goods_quantity);
            ivGoodsImage = itemView.findViewById(R.id.iv_goods_image);
        }
    }
}
