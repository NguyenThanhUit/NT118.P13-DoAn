package com.example.doan.goods;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;
import com.example.doan.databinding.CreateGoodsBinding;
public class AddNewGoodsActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private CreateGoodsBinding createGoodsBinding;
    private AddNewGoodsClick handlers;
    private Goods goods;
    private GoodsViewModel goodsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodsViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);

        goods = new Goods();
        createGoodsBinding = DataBindingUtil.setContentView(this, R.layout.create_goods);
        handlers = new AddNewGoodsClick(goods, this, goodsViewModel);
        createGoodsBinding.setGoods(goods);
        createGoodsBinding.setClickHandler(handlers);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            handlers.setSelectedImageUri(selectedImageUri);
            ImageView imageView = findViewById(R.id.selectedImageView);
            imageView.setImageURI(selectedImageUri);
        }

    }
}
