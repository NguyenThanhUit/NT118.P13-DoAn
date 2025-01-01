package com.example.doan.goods;

import android.content.Context;
import android.view.View;

public class AddRemoveNewQuantityForEachItem {

    private Goods goods;
    private Context context;
    private GoodsViewModel goodsViewModel;

    public AddRemoveNewQuantityForEachItem(Goods goods, Context context, GoodsViewModel goodsViewModel) {
        this.goods = goods;
        this.context = context;
        this.goodsViewModel = goodsViewModel;
    }

    public void onUpdateBtnClicked(View view) {
        int updatedQuantity = goods.getGOQuantity();
        goods.setGOQuantity(updatedQuantity);
        goodsViewModel.updateGoodsQuantity(goods.getGID(), updatedQuantity);
    }

}
