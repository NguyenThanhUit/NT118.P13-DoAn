package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.doan.R;

public class TaskLookupFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_lookup, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Task Lookup");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.getNavigationIcon().setTint(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(v -> replaceFragment(new HomeFragment()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout3, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
