package com.example.doan.orders;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.doan.domain.employee.EmployeeDetails;
import com.example.doan.domain.employee.EmployeeOrderTotal;

import java.util.List;

@Dao
public interface OrdersDAO {
    @Query("SELECT * FROM orders_infomation")
    LiveData<List<Orders>> getALLOrders();

    @Insert
    void insertOrder(Orders orders);

    @Delete
    void deleteOrder(Orders orders);

    @Query("SELECT SUM(order_total) FROM orders_infomation")
    LiveData<Float> getTotalOrderRevenue();

    @Query("SELECT employee_id, SUM(order_total) as totalOrder FROM orders_infomation GROUP BY employee_id ORDER BY totalOrder DESC LIMIT 5")
    LiveData<List<EmployeeOrderTotal>> getTopEmployees();

    @Query("SELECT e.employee_id, e.employee_name, e.employee_phone, e.employee_email, SUM(o.order_total) as totalOrder " +
            "FROM employees_information e " +
            "JOIN orders_infomation o ON e.employee_id = o.employee_id " +
            "GROUP BY e.employee_id, e.employee_name, e.employee_phone, e.employee_email " +
            "ORDER BY totalOrder DESC LIMIT 5")
    LiveData<List<EmployeeDetails>> getTopEmployeesWithDetails();
}
