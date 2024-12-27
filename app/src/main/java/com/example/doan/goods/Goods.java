package com.example.doan.goods;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "goods_information")
public class Goods implements Parcelable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "goods_id")
    private String gID;

    @ColumnInfo(name = "goods_name")
    private String gName;

    @ColumnInfo(name = "goods_image")
    private String gImage;

    @ColumnInfo(name = "goods_price")
    private String gPrice;

    @ColumnInfo(name = "goods_quantity")
    private String gQuantity;

    @ColumnInfo(name = "goods_order_quantity")
    private int gOQuantity;

    public Goods(@NonNull String gID, String gName, String gImage, String gPrice, String gQuantity, int gOQuantity) {
        this.gID = gID;
        this.gName = gName;
        this.gImage = gImage;
        this.gPrice = gPrice;
        this.gQuantity = gQuantity;
        this.gOQuantity = gOQuantity;
    }

    public Goods() {
        this.gOQuantity = 0;
    }

    protected Goods(Parcel in) {
        gID = in.readString();
        gName = in.readString();
        gImage = in.readString();
        gPrice = in.readString();
        gQuantity = in.readString();
        gOQuantity = in.readInt();
    }

    public static final Creator<Goods> CREATOR = new Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel in) {
            return new Goods(in);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };

    @NonNull
    public String getGID() {
        return gID;
    }

    public void setGID(@NonNull String gID) {
        this.gID = gID;
    }

    public String getGName() {
        return gName;
    }

    public void setGName(String gName) {
        this.gName = gName;
    }

    public String getGImage() {
        return gImage;
    }

    public void setGImage(String gImage) {
        this.gImage = gImage;
    }

    public String getGPrice() {
        return gPrice;
    }

    public void setGPrice(String gPrice) {
        this.gPrice = gPrice;
    }

    public String getGQuantity() {
        return gQuantity;
    }

    public void setGQuantity(String gQuantity) {
        this.gQuantity = gQuantity;
    }

    public int getGOQuantity() {
        return gOQuantity;
    }

    public void setGOQuantity(int gOQuantity) {
        this.gOQuantity = gOQuantity;
    }

    @Override
    public int describeContents() {
        return 0; // Not used, so we return 0
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(gID);
        parcel.writeString(gName);
        parcel.writeString(gImage);
        parcel.writeString(gPrice);
        parcel.writeString(gQuantity);
        parcel.writeInt(gOQuantity);
    }

}
