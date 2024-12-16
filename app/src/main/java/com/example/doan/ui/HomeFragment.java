package com.example.doan.ui;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.R;
import com.example.doan.data.DBAdapter;
import com.example.doan.domain.contact.ContactDto;

import java.util.List;


public class HomeFragment extends Fragment {

    private DBAdapter dbAdapter;
    private List<ContactDto> contactsData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        CardView cardViewReport = view.findViewById(R.id.card_view_report);
        CardView cardViewTask = view.findViewById(R.id.card_view_task);

        cardViewReport.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout3, new ReportLookupFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
        cardViewTask.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout3, new TaskLookupFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
//        dbAdapter = new DBAdapter(getContext());
//        dbAdapter.open();
//        dbAdapter.deleteAllUsers();

        return view;
    }
}
