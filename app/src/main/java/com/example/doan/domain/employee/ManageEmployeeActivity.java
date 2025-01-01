package com.example.doan.domain.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ActivityManageEmployeeBinding;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeeActivity extends AppCompatActivity {
    private AdapterForEmployee myAdapter;
    private EmployeeViewModel myViewModel;
    private ActivityManageEmployeeBinding binding;
    private AddNewEmployeeClick addNewEmployeeClick;
    ImageButton btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityManageEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnadd = findViewById(R.id.btnAddEmployee);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManageEmployeeActivity.this, AddNewEmployeeActivity.class);
                startActivity(i);
                finish();
            }
        });


        RecyclerView recyclerView = binding.rvEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);




        myAdapter = new AdapterForEmployee(new ArrayList<>());
        recyclerView.setAdapter(myAdapter);


        myViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);


        addNewEmployeeClick = new AddNewEmployeeClick(null, this, myViewModel);

        LiveData<List<Employees>> employeesLiveData = myViewModel.getAllemployees();
        employeesLiveData.observe(this, new Observer<List<Employees>>() {
            @Override
            public void onChanged(List<Employees> employees) {
                if (employees != null) {
                    myAdapter.setEmployeeLists((ArrayList<Employees>) employees);
                    myAdapter.notifyDataSetChanged();
                } else {

                    myAdapter.setEmployeeLists(new ArrayList<>());
                }
            }
        });
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Employees employeeToDelete = myAdapter.getEmployeeLists().get(position);


                myViewModel.deleteEmployee(employeeToDelete);

                Toast.makeText(ManageEmployeeActivity.this, "Employee deleted", Toast.LENGTH_SHORT).show();
            }
        };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        myAdapter.setOnItemClickListener(employee -> {
            Intent intent = new Intent(ManageEmployeeActivity.this, EmployeeInfoActivity.class);
            intent.putExtra("employeeName", employee.getName());
            intent.putExtra("role", employee.getPosition());
            intent.putExtra("email", employee.getEmail());
            intent.putExtra("phoneNumber", employee.getPhone());
            startActivity(intent);
        });
    }
}