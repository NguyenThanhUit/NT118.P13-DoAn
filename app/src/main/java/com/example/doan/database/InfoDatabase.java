package com.example.doan.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.doan.customers.CustomerDAO;
import com.example.doan.customers.Customers;
import com.example.doan.domain.employee.EmployeeDao;
import com.example.doan.domain.employee.Employees;
import com.example.doan.goods.Goods;
import com.example.doan.goods.GoodsDAO;
import com.example.doan.orders.Orders;
import com.example.doan.orders.OrdersDAO;
import com.example.doan.reports.Reports;
import com.example.doan.reports.ReportsDAO;
import com.example.doan.tasks.Tasks;
import com.example.doan.tasks.TasksDAO;

@Database(entities = {Employees.class, Customers.class, Reports.class, Tasks.class, Goods.class, Orders.class}, version = 19)
public abstract class InfoDatabase extends RoomDatabase {

    //Chi mot query duoc thuc hien cung mot luc
    private static InfoDatabase dbInstance;

    // Getter for CustomerDAO
    public abstract CustomerDAO getDBDAO();

    //Getter for EmployeeDAO
    public abstract EmployeeDao getEDAO();

    //Getter for ReportDAO
    public abstract ReportsDAO getRDAO();

    //Getter for TaskDAO
    public abstract TasksDAO getTDAO();

    //Getter for GoodDAO
    public abstract GoodsDAO getGDAO();

    //Getter for OderDAO
    public abstract OrdersDAO getODAO();

    public static synchronized InfoDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    InfoDatabase.class,
                    "info_database"
            ).fallbackToDestructiveMigration().build(); //Dam bao khi update version thi tai nguyen duoc giua lai
        }
        return dbInstance;
    }
}
