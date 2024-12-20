package com.example.doan.reports;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.databinding.ItemReportsBinding;

import java.util.ArrayList;

public class AdapterForReport extends RecyclerView.Adapter<AdapterForReport.ReportViewHolder> {
    private ArrayList<Reports> reports;

    public AdapterForReport(ArrayList<Reports> reports) {
        this.reports = reports;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReportsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_reports,
                parent,
                false
        );
        return new ReportViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Reports currentReport = reports.get(position);
        holder.binding.setReport(currentReport);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return reports == null ? 0 : reports.size();
    }

    public void setReportsLists(ArrayList<Reports> newReports) {
        this.reports = newReports;
        notifyDataSetChanged();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        final ItemReportsBinding binding;

        public ReportViewHolder(@NonNull ItemReportsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public ArrayList<Reports> getReportsList(){
        return  reports;
    }
}
