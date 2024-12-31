package com.example.doan.customers;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CustomerDAO {


    @Insert
    void insertCustomer(Customers customer);


    @Query("SELECT * FROM customers_information")
    LiveData<List<Customers>> getAllCustomers();

    @Query("DELETE  FROM customers_information")
    void deleteAllCustomers();

    @Delete
    void deleteCustomer(Customers customers);

    @Update
    void updateCustomer(Customers customer);

    @Query("SELECT COUNT(*) FROM customers_information WHERE customer_category = :category")
    LiveData<Integer> countCustomersByCategory(String category);

    @Query("SELECT COUNT(*) FROM customers_information")
    LiveData<Integer> countAllCustomers();


}
