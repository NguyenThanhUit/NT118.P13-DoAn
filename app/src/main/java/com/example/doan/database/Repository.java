package com.example.doan.database;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.doan.customers.CustomerDAO;
import com.example.doan.customers.Customers;
import com.example.doan.domain.employee.EmployeeDao;
import com.example.doan.domain.employee.Employees;
import com.example.doan.order.Goods;
import com.example.doan.order.GoodsDAO;
import com.example.doan.reports.Reports;
import com.example.doan.reports.ReportsDAO;
import com.example.doan.tasks.Tasks;
import com.example.doan.tasks.TasksDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final CustomerDAO dbdao;

    private final ExecutorService executorService;

    private final EmployeeDao edao;

    private final ReportsDAO rdao;

    private final TasksDAO tdao;

    private final GoodsDAO gDao;

    Handler handler = new Handler(Looper.myLooper());

    public Repository(Application application) {
        InfoDatabase infoDatabase = InfoDatabase.getInstance(application);

        //EmployeeDAO
        this.edao = infoDatabase.getEDAO();

        //CustomerDAO
        this.dbdao = infoDatabase.getDBDAO();

        //ReportDAO
        this.rdao = infoDatabase.getRDAO();

        //TaskDAO
        this.tdao = infoDatabase.getTDAO();

        //GoodsDAO
        this.gDao = infoDatabase.getGDAO();


        this.executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.myLooper());
    }


    // Thêm một khách hàng mới
    public void addCustomer(Customers customer) {
        executorService.execute(() -> dbdao.insertCustomer(customer));
    }
    public void deleteCustomer(Customers customers) {
        executorService.execute(() -> dbdao.deleteCustomer(customers));
    }

    //Them mot employee
    public void addEmployee(Employees employees) {
        executorService.execute(() -> edao.insertEmployeee(employees));
    }

    // Xoa mot employee
    public void deleteEmployee(Employees employees) {
        executorService.execute(() -> edao.deleteEmployee(employees));
    }


    //Xoa tat ca employee
    public void deleteAllCustomer() {
        executorService.execute(() -> dbdao.deleteAllCustomers());
    }


    // Lấy danh sách tất cả người dùng
    public LiveData<List<Employees>> getAllEmployees() {
        return edao.getALLEmloyees();
    }

    // Lấy danh sách tất cả khách hàng
    public LiveData<List<Customers>> getAllCustomers() {
        return dbdao.getAllCustomers();
    }

    //Them mot report moi
    public void addnewReport(Reports reports){
        executorService.execute(() -> rdao.insertReport(reports));
    }
    public void deleteReport(Reports reports){
        executorService.execute(() -> rdao.deleteReport(reports));
    }
    public LiveData<List<Reports>> getAllReports(){
        return rdao.getALlReports();
    }
    public void addnewTask(Tasks tasks){
        executorService.execute(() -> tdao.insertTask(tasks));
    }
    public void deleteTask(Tasks tasks){
        executorService.execute(() -> tdao.deleteTask(tasks));
    }
    public LiveData<List<Tasks>> getALLTasks(){
        return tdao.getALLTasks();
    }

    public LiveData<List<Goods>> getALLGoods(){
        return gDao.getALLGoods();
    }
    public void addnewGoods(Goods goods){
        executorService.execute(() -> gDao.insertGoods(goods));
    }
    public void deleteGoods(Goods goods){
        executorService.execute(() -> gDao.deleteGoods(goods));
    }
    public void updateGoodsQuantity(String goodsId, int newQuantity) {
        gDao.updateGoodsQuantity(goodsId, newQuantity);
    }


}
