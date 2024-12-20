package com.example.doan.order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.doan.database.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsViewModel extends AndroidViewModel {
    public Repository repository;
    public LiveData<List<Goods>> allGoods;

    private MutableLiveData<Map<String, Integer>> goodsQuantityMap = new MutableLiveData<>(new HashMap<>());

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
        Map<String, Integer> currentMap = goodsQuantityMap.getValue();
        if (currentMap != null) {
            currentMap.put(goodsId, newQuantity);
            goodsQuantityMap.setValue(currentMap);
        }
    }


}
