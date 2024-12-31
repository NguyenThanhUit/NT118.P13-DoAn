package com.example.doan.orders;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrdersDAO {
    @Query("SELECT * FROM orders_infomation")
    LiveData<List<Orders>> getALLOrders();

    @Insert
    void insertOrder(Orders orders);

    @Delete
    void deleteOrder(Orders orders);

    @Query("SELECT SUM(order_total) FROM orders_infomation")
    LiveData<Float> getTotalOrderRevenue();
}
