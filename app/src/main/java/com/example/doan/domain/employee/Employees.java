package com.example.doan.domain.employee;

import androidx.annotation.NonNull;
import androidx.annotation.ReturnThis;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "employees_information")
public class Employees {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "employee_id")
    private String eid;

    @ColumnInfo(name = "employee_name")
    private String name;

    @ColumnInfo(name = "employee_phone")
    private String phone;

    @ColumnInfo(name = "employee_email")
    private String email;

    @ColumnInfo(name = "employee_position")
    private String position;

    @ColumnInfo(name = "employee_username")
    private String username;

    @ColumnInfo(name = "employee_password")
    private String password;

    @ColumnInfo(name = "employee_createdat")
    private String createdAt;

    @ColumnInfo(name = "employee_updatedAt")
    private String updateAt;

    public Employees( String phone, String name, String position, String email, String username, String createdAt, String password, String updateAt) {
        this.eid = generateEID();
        this.phone = phone;
        this.name = name;
        this.position = position;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
        this.password = password;
        this.updateAt = updateAt;

    }

    public Employees() {

    }

    private String generateEID(){
        return "NV" + (System.currentTimeMillis() % 1000000);
    }

    @NonNull
    public String getEid() {
        return eid;
    }

    public void setEid(@NonNull String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return eid + " - " + name;
    }
}
