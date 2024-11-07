package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.doan.R;

public class ReportLookupFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_lookup, container, false);

        // Nút Quay Lại
        Button btnBack = view.findViewById(R.id.btnBackToHomeFromReport);
        btnBack.setOnClickListener(v -> replaceFragment(new HomeFragment()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout3, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}