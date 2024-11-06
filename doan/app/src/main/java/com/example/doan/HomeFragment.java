package com.example.doan;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


public class HomeFragment extends Fragment {

    private DBAdapter dbAdapter;
    private List<Contacts> contactsData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAdapter = new DBAdapter(getContext());
        dbAdapter.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        dbAdapter = new DBAdapter(getContext());
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        return view;
    }
}