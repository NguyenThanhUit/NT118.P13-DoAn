package com.example.doan.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.MainActivity;
import com.example.doan.MainActivityForSaleEmployee;
import com.example.doan.R;
import com.example.doan.database.InfoDatabase;
import com.example.doan.tasks.Tasks;
import com.example.doan.tasks.TasksDAO;


import java.util.ArrayList;
import java.util.List;

public class TaskLookupFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskLookUpAdapter taskAdapter;
    private ArrayList<com.example.doan.ui.TasksLookUP> taskList;
    private EditText editTextSearch;
    private Button btnSearch;
    private LiveData<List<Tasks>> tasks;
    private TasksDAO tasksDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_lookup, container, false);


        recyclerView = view.findViewById(R.id.rvTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        editTextSearch = view.findViewById(R.id.edtSearchTask);
        btnSearch = view.findViewById(R.id.btnSearchTask);

        btnSearch.setOnClickListener(v -> {
            String searchKeyword = editTextSearch.getText().toString().trim();
            ArrayList<com.example.doan.ui.TasksLookUP> filteredTasks = new ArrayList<>();

            for (com.example.doan.ui.TasksLookUP task : taskList) {
                if (task.getTaskName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                    filteredTasks.add(task);
                }
            }

            if (!filteredTasks.isEmpty()) {
                taskAdapter = new TaskLookUpAdapter(getContext(), filteredTasks);
                recyclerView.setAdapter(taskAdapter);
                taskAdapter.setOnItemClickListener(task -> showTaskInfo(task));
                taskAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "No matching tasks found", Toast.LENGTH_SHORT).show();
            }
        });


        Bundle args = getArguments();
        if (args != null) {
            taskList = args.getParcelableArrayList("TASKS");
        }

        if (taskList != null && !taskList.isEmpty()) {
            Log.d("TaskLookupFragment", "Task list size: " + taskList.size());
            taskAdapter = new TaskLookUpAdapter(getContext(), taskList);
            recyclerView.setAdapter(taskAdapter);
            taskAdapter.setOnItemClickListener(task -> showTaskInfo(task));
            taskAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "No tasks available", Toast.LENGTH_SHORT).show();
            Log.d("TaskLookupFragment", "No tasks available");
        }

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

        return view;
    }


    public void showTaskInfo(TasksLookUP taskLookUP) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.employee_task_infor, null);

        TextView tvTitle = dialogView.findViewById(R.id.tv_task_title);
        TextView tvCreateDate = dialogView.findViewById(R.id.tv_task_start_value);
        TextView tvEndDate = dialogView.findViewById(R.id.tv_task_end_value);
        TextView tvDescription = dialogView.findViewById(R.id.tv_task_description_value);

        tvTitle.setText(taskLookUP.getTaskNotes());
        tvCreateDate.setText(taskLookUP.getTaskAssignedDate());
        tvEndDate.setText(taskLookUP.getTaskDueDate());
        tvDescription.setText(taskLookUP.getTaskDecription());

        Button btnComplete = dialogView.findViewById(R.id.btn_task_complete);
        btnComplete.setOnClickListener(view -> {
            Log.d("TaskLookupFragment", "Marking task as complete: " + taskLookUP.getTaskName());
            taskLookUP.setTaskStatus("Hoàn thành");

            new Thread(() -> {
                try {
                    tasksDAO = InfoDatabase.getInstance(getContext()).getTDAO();


                    tasksDAO.updateTaskStatus(taskLookUP.getTaskName(), "Hoàn thành");
                    Log.d("TaskLookupFragment", "Task status updated in database for ID: " + taskLookUP.getTaskName());


                    tasks = (LiveData<List<Tasks>>) tasksDAO.getALLTasks();

                    getActivity().runOnUiThread(() -> {
                        if (tasks != null && taskAdapter != null) {
                            taskAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Task status updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error updating task status", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.e("TaskLookupFragment", "Error updating task status: " + e.getMessage());
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Failed to update task status", Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();
        });

        builder.setView(dialogView);
        builder.create().show();
    }







    // Replace fragments
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
