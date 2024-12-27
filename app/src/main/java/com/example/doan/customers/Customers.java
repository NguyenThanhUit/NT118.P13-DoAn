package com.example.doan.customers;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "customers_information")
public class Customers {

    @NonNull
    @PrimaryKey()
    @ColumnInfo(name = "customer_id")
    private String cid;

    @ColumnInfo(name = "customer_name")
    private String name;

    @ColumnInfo(name = "customer_phone")
    private String phone;

    @ColumnInfo(name = "customer_email")
    private String email;

    @ColumnInfo(name = "customer_address")
    private String address;

    @ColumnInfo(name = "customer_category")
    private String category;

    @ColumnInfo(name = "customer_createdat")
    private String createdat;

    @ColumnInfo(name = "customer_updatedat")
    private String updatedat;

    public Customers() {
    }

    public Customers(String cid, String name, String phone, String email, String address, String category, String createdat, String updatedat) {
        this.cid = cid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.category = category;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    @Override
    public String toString() {
        return name;
    }
}
