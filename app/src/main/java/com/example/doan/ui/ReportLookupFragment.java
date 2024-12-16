package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.doan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReportLookupFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_lookup, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Report Lookup");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.getNavigationIcon().setTint(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(v -> replaceFragment(new HomeFragment()));

        FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> openCreateAReport());

        return view;
    }

    private void openCreateAReport() {
        CreateReportFragment openCreateAReport = new CreateReportFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout3, openCreateAReport);
        transaction.addToBackStack(null); // Để quay lại khi nhấn nút Back
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout3, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
