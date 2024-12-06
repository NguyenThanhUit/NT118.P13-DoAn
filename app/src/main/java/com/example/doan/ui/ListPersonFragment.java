package com.example.doan.ui;

import com.example.doan.ModifyContact;
import com.example.doan.R;
import com.example.doan.data.DBAdapter;
import com.example.doan.domain.contact.Contacts;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListPersonFragment extends Fragment {

    private DBAdapter dbAdapter;
    private ListView lvContacts;
    private ContactsAdapter contactsAdapter;
    private List<Contacts> contactsData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAdapter = new DBAdapter(getContext());
        dbAdapter.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_person, container, false);
        lvContacts = view.findViewById(R.id.lvPerson);

        dbAdapter.deleteAllUsers();
        insertSampleData();
        showData();
        setupListViewLongClickListener();

        return view;
    }

    private void insertSampleData() {
        for (int i = 0; i < 10; i++) {
            String maKH = "MAKH" + i;
            String name = "Nguyễn Văn An " + i;
            String phone = "012345678" + i;
            String address = "123" + i;


            Cursor cursor = dbAdapter.getUserByMAKH(maKH);
            if (cursor != null && cursor.getCount() == 0) {
                dbAdapter.createContacts(maKH, name, address, phone);
            }
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    private List<Contacts> getData() {
        List<Contacts> contacts = new ArrayList<>();
        Cursor cursor = dbAdapter.getAllUsers(null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String maKH = cursor.getString(cursor.getColumnIndexOrThrow(DBAdapter.KEY_MAKH));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBAdapter.KEY_HOTEN));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DBAdapter.KEY_PHONE));
                String addr = cursor.getString(cursor.getColumnIndexOrThrow(DBAdapter.KEY_ADDRESS));
                contacts.add(new Contacts(maKH, name, phone, addr));
            }
            cursor.close();
        }
        return contacts;
    }

    private void showData() {
        contactsData = getData();
        contactsAdapter = new ContactsAdapter(getActivity(), contactsData);
        lvContacts.setAdapter(contactsAdapter);
        contactsAdapter.notifyDataSetChanged();
    }

    private void setupListViewLongClickListener() {
        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contacts selectedContact = (Contacts) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Đã chọn: " + selectedContact.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), ModifyContact.class);
                intent.putExtra("MAKH", selectedContact.getMaKH());
                startActivityForResult(intent, 1);
                return true;
            }
        });
        showData();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            showData();
        }
    }

    public static class ContactsAdapter extends ArrayAdapter<Contacts> {

        public ContactsAdapter(Context context, List<Contacts> contacts) {
            super(context, 0, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            Contacts contact = getItem(position);
            TextView textViewMAKH = convertView.findViewById(R.id.tvMAKH);
            TextView textViewName = convertView.findViewById(R.id.tvName);
            if (contact != null) {
                textViewMAKH.setText(contact.getMaKH());
                textViewName.setText(contact.getName());
            }

            return convertView;
        }
    }
<<<<<<< Updated upstream
=======

    // Phương thức thay đổi trạng thái khi Button được chọn
    private void onFilterClicked(TextView newSelectedTextView) {
        /// Đặt lại trạng thái của TextView trước đó nếu có
        if (selectedTextView != null) {
            selectedTextView.setSelected(false);
            selectedTextView.setTextColor(getResources().getColor(R.color.color2));
        }

        // Đặt trạng thái được chọn cho TextView mới
        newSelectedTextView.setSelected(true);
        newSelectedTextView.setTextColor(getResources().getColor(R.color.color3));
        selectedTextView = newSelectedTextView;
    }

    private void updateFilterCounts() {
        // Lấy số lượng khách hàng cho từng bộ lọc
        countAll = dbAdapter.getCountByFilter("Tất cả");
        countNew = dbAdapter.getCountByFilter("Mới");
        countNotApproach = dbAdapter.getCountByFilter("Chưa tiếp");
        countApproach = dbAdapter.getCountByFilter("Tiếp cận");
        countHot = dbAdapter.getCountByFilter("Nóng");
        countPotential = dbAdapter.getCountByFilter("Tiềm năng");

        // Cập nhật số lượng vào các TextView với cấu trúc hiển thị như trong ảnh
        tvFilterAll.setText(" Tất cả " + formatCount(countAll));
        tvFilterNew.setText(" Mới " + formatCount(countNew));
        tvFilterNotApproach.setText(" Chưa tiếp cận " + formatCount(countNotApproach));
        tvFilterApproach.setText(" Tiếp cận " + formatCount(countApproach));
        tvFilterHot.setText(" Nóng " + formatCount(countHot));
        tvFilterPotential.setText(" Tiềm năng " + formatCount(countPotential));
    }

    private String formatCount(int count) {
        return "(" + (count > 0 ? String.valueOf(count) : "0") + ")";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbAdapter.close(); // Đóng kết nối khi view bị hủy
    }

    private void openAddCustomerFragment() {
        AddCustomerFragment addCustomerFragment = new AddCustomerFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout3, addCustomerFragment);
        transaction.addToBackStack(null); // Để quay lại khi nhấn nút Back
        transaction.commit();
    }
>>>>>>> Stashed changes
}

