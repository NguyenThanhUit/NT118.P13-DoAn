package com.example.doan.goods;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.doan.database.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GoodsViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Goods>> allGoods;
    private static final String TAG = "GoodsViewModel";

    private MutableLiveData<Map<String, Integer>> goodsQuantityMap = new MutableLiveData<>(new HashMap<>());


    private Executor executor = Executors.newSingleThreadExecutor();

    public GoodsViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        allGoods = repository.getALLGoods();
    }

    public LiveData<List<Goods>> getAllGoods() {
        return allGoods;
    }

    public void addnewGoods(Goods goods) {
        repository.addnewGoods(goods);
    }

    public void deleteGoods(Goods goods) {
        repository.deleteGoods(goods);
    }

    public void updateGoodsQuantity(String goodsId, int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        Log.d(TAG, "Updating quantity for Goods ID: " + goodsId + " to: " + newQuantity);

        executor.execute(() -> {
            Map<String, Integer> currentMap = goodsQuantityMap.getValue();
            if (currentMap != null) {
                currentMap.put(goodsId, newQuantity);
                goodsQuantityMap.postValue(currentMap);
            }
            repository.updateGoodsQuantity(goodsId, newQuantity);
        });
    }
}
