package com.example.doan.order;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GoodsDAO {
    @Query("SELECT * FROM GOODS_ORDER")
    LiveData<List<Goods>> getALLGoods();

    @Insert
    void insertGoods(Goods goods);

    @Delete
    void deleteGoods(Goods goods);

    @Query("UPDATE GOODS_ORDER SET goods_order_quantity = :quantity WHERE goods_id = :goodsId")
    void updateGoodsQuantity(String goodsId, int quantity);

}
