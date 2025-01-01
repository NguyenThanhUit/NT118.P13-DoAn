package com.example.doan.goods;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GoodsDAO {
    @Query("SELECT * FROM goods_information")
    LiveData<List<Goods>> getALLGoods();

    @Insert
    void insertGoods(Goods goods);

    @Delete
    void deleteGoods(Goods goods);

    @Query("UPDATE goods_information SET goods_order_quantity = :quantity WHERE goods_id = :goodsId")
    void updateGoodsQuantity(String goodsId, int quantity);

    @Update
    void updateGoods(Goods goods);

    @Query("SELECT EXISTS(SELECT 1 FROM goods_information WHERE goods_id = :id)")
    LiveData<Boolean> isIDExists(String id);


}
