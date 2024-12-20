package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.customers.AddNewCustomerClick;
import com.example.doan.customers.Customers;
import com.example.doan.customers.AdapterForCustomer;
import com.example.doan.customers.CustomerViewModel;
import com.example.doan.customers.CustomerClickHandler;
import com.example.doan.databinding.FragmentListPersonBinding;

import java.util.ArrayList;
import java.util.List;

public class ListPersonFragment extends Fragment {
    private AdapterForCustomer myAdapter;
    private CustomerViewModel myViewModel;
    private FragmentListPersonBinding binding;
    private CustomerClickHandler personClickHandler;
    private AddNewCustomerClick addNewCustomerClick;

    private Button fabAll, fabNew, fabNA, fabA, fabHot, fabPotential;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListPersonBinding.inflate(inflater, container, false);

        // Set up RecyclerView
        RecyclerView recyclerView = binding.recycview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        myAdapter = new AdapterForCustomer(new ArrayList<>());
        recyclerView.setAdapter(myAdapter);


        myViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        personClickHandler = new CustomerClickHandler(getContext());
        addNewCustomerClick = new AddNewCustomerClick(new Customers(), getContext(), myViewModel);

        binding.setClickHandler(personClickHandler);


        fabAll = binding.getRoot().findViewById(R.id.btnFilterAll);
        fabNew = binding.getRoot().findViewById(R.id.btnFilterNew);
        fabNA = binding.getRoot().findViewById(R.id.btnFilterNotApproach);
        fabA = binding.getRoot().findViewById(R.id.btnFilterApproach);
        fabHot = binding.getRoot().findViewById(R.id.btnFilterHot);
        fabPotential = binding.getRoot().findViewById(R.id.btnFilterPotential);


        fabAll.setOnClickListener(v -> filterCustomers("Tất cả"));
        fabNew.setOnClickListener(v -> filterCustomers("Mới"));
        fabNA.setOnClickListener(v -> filterCustomers("Chưa tiếp cận"));
        fabA.setOnClickListener(v -> filterCustomers("Tiếp cận"));
        fabHot.setOnClickListener(v -> filterCustomers("Nóng"));
        fabPotential.setOnClickListener(v -> filterCustomers("Tiềm năng"));


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // No need to support drag-and-drop
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Customers customerToDelete = myAdapter.getCustomerList().get(position);
                myViewModel.deleteCustomer(customerToDelete);
                Toast.makeText(getContext(), "Customer deleted", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return binding.getRoot();
    }
    private void filterCustomers(String category) {
        LiveData<List<Customers>> customersLiveData = myViewModel.getAllcustomer();
        customersLiveData.observe(getViewLifecycleOwner(), customers -> {
            ArrayList<Customers> filteredCustomers = new ArrayList<>();
            for (Customers customer : customers) {
                if ("Tất cả".equalsIgnoreCase(category)) {
                    filteredCustomers.add(customer);
                } else if ("Mới".equalsIgnoreCase(category) && "Mới".equalsIgnoreCase(customer.getCategory())) {
                    filteredCustomers.add(customer);
                } else if ("Chưa tiếp cận".equalsIgnoreCase(category) && "Chưa tiếp cận".equalsIgnoreCase(customer.getCategory())) {
                    filteredCustomers.add(customer);
                } else if ("Tiếp cận".equalsIgnoreCase(category) && "Tiếp cận".equalsIgnoreCase(customer.getCategory())) {
                    filteredCustomers.add(customer);
                } else if ("Nóng".equalsIgnoreCase(category) && "Nóng".equalsIgnoreCase(customer.getCategory())) {
                    filteredCustomers.add(customer);
                } else if ("Tiềm năng".equalsIgnoreCase(category) && "Tiềm năng".equalsIgnoreCase(customer.getCategory())) {
                    filteredCustomers.add(customer);
                }
            }


            myAdapter.setCustomer(filteredCustomers);
            myAdapter.notifyDataSetChanged();
        });

        Toast.makeText(getContext(), "Filtered by: " + category, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Observe the LiveData from the ViewModel
        LiveData<List<Customers>> customersLiveData = myViewModel.getAllcustomer();
        customersLiveData.observe(getViewLifecycleOwner(), new Observer<List<Customers>>() {
            @Override
            public void onChanged(List<Customers> customers) {
                myAdapter.setCustomer(new ArrayList<>(customers));
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
