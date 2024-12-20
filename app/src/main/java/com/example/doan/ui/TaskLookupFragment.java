package com.example.doan.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.MainActivity;
import com.example.doan.MainActivityForSaleEmployee;
import com.example.doan.R;
import com.example.doan.ui.TasksLookUP;

import java.util.ArrayList;

public class TaskLookupFragment extends Fragment {
    private TaskLookUpAdapter taskAdapter;
    private ArrayList<TasksLookUP> taskList;
    private ListView listView;
    private EditText editTextSearch;
    private Button btnSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.task_lookup, container, false);

        listView = view.findViewById(R.id.lvTasks);
        editTextSearch = view.findViewById(R.id.edtSearchTask);
        btnSearch = view.findViewById(R.id.btnSearchTask);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKeyword = editTextSearch.getText().toString().trim();
                ArrayList<TasksLookUP> filteredTasks = new ArrayList<>();

                for (TasksLookUP task : taskList) {
                    if (task.getTaskName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        filteredTasks.add(task);
                    }
                }

                if (!filteredTasks.isEmpty()) {
                    taskAdapter = new TaskLookUpAdapter(getContext(), filteredTasks);
                    listView.setAdapter(taskAdapter);
                    taskAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No matching tasks found", Toast.LENGTH_SHORT).show();
                }
            }
        });



        Button btnBack = view.findViewById(R.id.btnBackToHomeFromTask);
        btnBack.setOnClickListener(v -> {

            String userName = getArguments() != null ? getArguments().getString("USER_NAME") : "Guest";


            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();

            bundle.putParcelableArrayList("TASKS", taskList);
            bundle.putString("USER_NAME", userName);

            homeFragment.setArguments(bundle);


            replaceFragment(homeFragment);
        });


        Bundle args = getArguments();
        if (args != null) {
            taskList = args.getParcelableArrayList("TASKS");
        }

        if (taskList != null && !taskList.isEmpty()) {
            Log.d("TaskLookupFragment", "Task list size: " + taskList.size());
            taskAdapter = new TaskLookUpAdapter(getContext(), taskList);
            listView.setAdapter(taskAdapter);
            taskAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "No tasks available", Toast.LENGTH_SHORT).show();
            Log.d("TaskLookupFragment", "No tasks available");
        }

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        if (getActivity() instanceof MainActivity) {

            transaction.replace(R.id.frameLayout3, fragment);
        } else if (getActivity() instanceof MainActivityForSaleEmployee) {

            transaction.replace(R.id.frameLayout4, fragment);
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }
}
