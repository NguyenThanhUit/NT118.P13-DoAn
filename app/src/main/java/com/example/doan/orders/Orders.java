package com.example.doan.orders;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.doan.customers.Customers;
import com.example.doan.domain.employee.Employees;
import com.example.doan.goods.Goods;

@Entity(
        tableName = "orders_infomation",
        foreignKeys = {
                @ForeignKey(
                        entity = Goods.class,
                        parentColumns = "goods_id",
                        childColumns = "goods_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Customers.class,
                        parentColumns = "customer_id",
                        childColumns = "customer_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Employees.class,
                        parentColumns = "employee_id",
                        childColumns = "employee_id",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Orders {
        @NonNull
        @PrimaryKey
        @ColumnInfo(name = "orders_id")
        private String oID;

        @ColumnInfo(name = "orders_price")
        private String oPrice;

        @ColumnInfo(name = "orders_sale")
        private String oSale;

        @ColumnInfo(name = "orders_vat")
        private String oVAT;

        @ColumnInfo(name = "order_total")
        private String oTotal;

        @ColumnInfo(name = "order_customer_payed")
        private String oCP;

        @ColumnInfo(name = "customer_id")
        private int customerID;

        @ColumnInfo(name = "goods_price")
        private String goodsPrice;

        @ColumnInfo(name = "goods_id")
        private String goodsID;

        @ColumnInfo(name = "employee_id")
        private String employeeID;

        public Orders(@NonNull  String oPrice, String oSale, String oVAT, String oTotal, String oCP, int customerID, String goodsPrice, String goodsID, String employeeID) {
                this.oID  = generateOID();
                this.oPrice = oPrice;
                this.oSale = oSale;
                this.oVAT = oVAT;
                this.oTotal = oTotal;
                this.oCP = oCP;
                this.customerID = customerID;
                this.goodsPrice = goodsPrice;
                this.goodsID = goodsID;
                this.employeeID = employeeID;
        }

        public Orders() {
        }

        private String generateOID() {
                return "ORDER" + System.currentTimeMillis();
        }

        @NonNull
        public String getOID() {
                return oID;
        }

        public void setOID(@NonNull String oID) {
                this.oID = oID;
        }

        public String getOPrice() {
                return oPrice;
        }

        public void setOPrice(String oPrice) {
                this.oPrice = oPrice;
        }

        public String getOSale() {
                return oSale;
        }

        public void setOSale(String oSale) {
                this.oSale = oSale;
        }

        public String getOVAT() {
                return oVAT;
        }

        public void setOVAT(String oVAT) {
                this.oVAT = oVAT;
        }

        public String getOTotal() {
                return oTotal;
        }

        public void setOTotal(String oTotal) {
                this.oTotal = oTotal;
        }

        public String getOCP() {
                return oCP;
        }

        public void setOCP(String oCP) {
                this.oCP = oCP;
        }

        public int getCustomerID() {
                return customerID;
        }

        public void setCustomerID(int customerID) {
                this.customerID = customerID;
        }

        public String getGoodsPrice() {
                return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
                this.goodsPrice = goodsPrice;
        }

        public String getGoodsID() {
                return goodsID;
        }

        public void setGoodsID(String goodsID) {
                this.goodsID = goodsID;
        }

        public String getEmployeeID() {
                return employeeID;
        }

        public void setEmployeeID(String employeeID) {
                this.employeeID = employeeID;
        }
}
