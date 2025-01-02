package com.example.doan.orders;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AddNewOrdersClick {
    private Orders orders;
    private Context context;
    private OrdersViewModel ordersViewModel;
    private OnOrderCreatedListener listener;

    public AddNewOrdersClick(Orders orders, Context context, OrdersViewModel ordersViewModel) {
        this.orders = orders;
        this.context = context;
        this.ordersViewModel = ordersViewModel;
    }

    public interface OnOrderCreatedListener {
        void onOrderCreated();
    }

    public void setOnOrderCreatedListener(OnOrderCreatedListener listener) {
        this.listener = listener;
    }

    public void onSubmitBtnClicked(View view) {
        if (orders.getCustomerID() == 0 || TextUtils.isEmpty(String.valueOf(orders.getCustomerID()))) {
            Toast.makeText(context, "Vui lòng chọn khách hàng!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(orders.getGoodsID()) ||
                TextUtils.isEmpty(orders.getOPrice()) ||
                TextUtils.isEmpty(orders.getOSale()) ||
                TextUtils.isEmpty(orders.getOVAT()) ||
                TextUtils.isEmpty(orders.getOTotal()) ||
                TextUtils.isEmpty(orders.getGoodsPrice()) ||
                TextUtils.isEmpty(orders.getEmployeeID())) {

            Toast.makeText(context, "Please fill in all ìnformation!", Toast.LENGTH_SHORT).show();
        } else {
            Orders newOrder = new Orders(
                    orders.getOPrice(),
                    orders.getOSale(),
                    orders.getOVAT(),
                    orders.getOTotal(),
                    orders.getOCP(),
                    orders.getCustomerID(),
                    orders.getGoodsPrice(),
                    orders.getGoodsID(),
                    orders.getEmployeeID()
            );
            ordersViewModel.addnewOrdcer(newOrder);
            Toast.makeText(context, "Add new order successfully!", Toast.LENGTH_SHORT).show();


            if (listener != null) {
                listener.onOrderCreated();
            }
        }
    }
}


