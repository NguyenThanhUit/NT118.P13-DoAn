package com.example.doan.orders;

import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.customers.Customers;
import com.example.doan.database.InfoDatabase;
import com.example.doan.databinding.CreateOrderBinding;
import com.example.doan.goods.AdapterForGoods;
import com.example.doan.goods.Goods;
import com.example.doan.goods.GoodsViewModel;

import java.util.ArrayList;

public class OrderTotal extends AppCompatActivity {
    private Orders orders;
    private OrdersViewModel ordersViewModel;
    private GoodsViewModel goodsViewModel;
    private ArrayList<Goods> goodsList = new ArrayList<>();
    private int sum = 0;
    private AdapterForGoods adapter;
    private CreateOrderBinding createOrderBinding;
    private AddNewOrdersClick handlers;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.create_order);

        ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        goodsViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);

        orders = new Orders();
        String employeeID = getIntent().getStringExtra("EMPLOYEE_ID");
        orders.setEmployeeID(employeeID);


        orders.setOSale(String.valueOf(0));
        orders.setOVAT(String.valueOf(0));
        orders.setOTotal(String.valueOf(0));

        createOrderBinding = DataBindingUtil.setContentView(this, R.layout.create_order);
        handlers = new AddNewOrdersClick(orders, this, ordersViewModel);
        createOrderBinding.setOrders(orders);
        createOrderBinding.setClickHandler(handlers);

        goodsList = getIntent().getParcelableArrayListExtra("goodsList");
        if (goodsList == null) {
            goodsList = new ArrayList<>();
        }

        InfoDatabase db = InfoDatabase.getInstance(this);
        Spinner spinner = findViewById(R.id.spinnerforCustomerOrder);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Customers selectedCustomer = (Customers) adapterView.getItemAtPosition(position);
                Log.d("SpinnerSelection", "Selected customer: " + selectedCustomer.getName() + ", CID: " + selectedCustomer.getCid());
                orders.setCustomerID(selectedCustomer.getCid());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        db.getDBDAO().getAllCustomers().observe(this, customers -> {
            ArrayAdapter<Customers> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, customers);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        });

        RecyclerView rv = findViewById(R.id.rv_order);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterForGoods(new ArrayList<>(), goodsViewModel);
        rv.setAdapter(adapter);

        goodsViewModel.getAllGoods().observe(this, goodsList -> {
            if (goodsList != null) {
                adapter.setData(new ArrayList<>(goodsList));
            }
        });

        for (Goods goods : goodsList) {
            if (goods.getGOQuantity() > 0) {
                try {
                    float price = Float.parseFloat(goods.getGPrice());
                    sum += goods.getGOQuantity() * price;
                    orders.setGoodsID(goods.getGID());
                    orders.setGoodsPrice(goods.getGPrice());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        TextView tvmoney = findViewById(R.id.tv_money);
        tvmoney.setText(String.valueOf(sum));
        String money = tvmoney.getText().toString();
        orders.setOPrice(money);


        EditText edtSale = findViewById(R.id.edt_sale);
        edtSale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String saleText = charSequence.toString();
                double sale = 0;

                if (!saleText.isEmpty()) {
                    try {
                        sale = Double.parseDouble(saleText) / 100;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }


                EditText edtAnotherCost = findViewById(R.id.edt_anothercost);
                TextView tvTotalCost = findViewById(R.id.tv_totalcost);
                calculateTotalCost(edtAnotherCost.getText().toString(), sum, sale, tvTotalCost);


                orders.setOSale(String.valueOf(sale));
                orders.setOVAT(String.valueOf(sale));
                orders.setOTotal(String.valueOf(sum * (1 - sale) + Double.parseDouble(edtAnotherCost.getText().toString()))); // Cập nhật tổng chi phí
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        EditText edtAnotherCost = findViewById(R.id.edt_anothercost);
        edtAnotherCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText edtSale = findViewById(R.id.edt_sale);
                double sale = 0;
                try {
                    sale = Double.parseDouble(edtSale.getText().toString()) / 100;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                calculateTotalCost(s.toString(), sum, sale, findViewById(R.id.tv_totalcost));
                orders.setOTotal(String.valueOf(sum * (1 - sale) + Double.parseDouble(s.toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void calculateTotalCost(String anotherCostInput, int sum, double sale, TextView tvTotalcost) {
        try {
            double anotherCost = anotherCostInput.isEmpty() ? 0 : Double.parseDouble(anotherCostInput);
            double totalCost = sum * (1 - sale) + anotherCost;
            tvTotalcost.setText(String.format("%.2f", totalCost));
        } catch (NumberFormatException e) {
            tvTotalcost.setText("Invalid cost");
        }
    }
}
