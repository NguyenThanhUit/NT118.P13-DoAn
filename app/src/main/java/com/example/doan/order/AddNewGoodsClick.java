package com.example.doan.order;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.doan.MainActivityForSaleEmployee;

public class AddNewGoodsClick {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Goods goods;
    private Context context;
    private GoodsViewModel goodsViewModel;
    private Uri selectedImageUri;

    public AddNewGoodsClick() {
        super();
    }

    public AddNewGoodsClick(Goods goods, Context context, GoodsViewModel goodsViewModel) {
        this.goods = goods;
        this.context = context;
        this.goodsViewModel = goodsViewModel;
    }

    public void openImagePicker(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ((Activity) context).startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }



    public void setSelectedImageUri(Uri uri) {
        this.selectedImageUri = uri;
        if (goods != null) {
            goods.setGImage(uri.toString());
        }
    }


    public void onSubmitBtnClicked(View view) {
        if (TextUtils.isEmpty(goods.getGID()) || TextUtils.isEmpty(goods.getGName()) || TextUtils.isEmpty(goods.getGPrice()) || TextUtils.isEmpty(goods.getGQuantity())) {
            Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            Goods newGoods = new Goods(
                    goods.getGID(),
                    goods.getGName(),
                    selectedImageUri != null ? selectedImageUri.toString() : "",
                    goods.getGPrice(),
                    goods.getGQuantity(),
                    goods.getGOQuantity()
            );
            goodsViewModel.addnewGoods(newGoods);  // Thêm hàng hóa vào ViewModel
            Toast.makeText(context, "Thêm hàng hóa thành công", Toast.LENGTH_SHORT).show();
        }
    }
}
