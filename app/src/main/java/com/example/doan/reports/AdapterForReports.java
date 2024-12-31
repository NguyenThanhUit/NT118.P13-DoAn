package com.example.doan.reports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.doan.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterForReports extends RecyclerView.Adapter<AdapterForReports.ReportViewHolder> {

    private List<Reports> reportsList;
    private OnReportLongClickListener longClickListener;
    private OnReportClickListener clickListener;

    public interface OnReportClickListener {
        void onReportClick(Reports report);
    }

    public void setOnReportClickListener(OnReportClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnReportLongClickListener {
        void onReportLongClick(Reports report);
    }

    public void setOnReportLongClickListener(OnReportLongClickListener listener) {
        this.longClickListener = listener;
    }

    public void setReportsList(List<Reports> reportsList) {
        this.reportsList = reportsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reports, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Reports report = reportsList.get(position);
        holder.tvTitle.setText(report.getReportTitle());
        holder.tvStartDate.setText(report.getStartDate());
        holder.tvEndDate.setText(report.getEndDate());

        // Xử lý nhấn lâu
        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onReportLongClick(report);
            }
            return true; // Trả về true để xác nhận sự kiện đã được xử lý
        });

        // Click vào item
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onReportClick(report);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportsList == null ? 0 : reportsList.size();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvStartDate, tvEndDate, tvDescription;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_report_title);
            tvStartDate = itemView.findViewById(R.id.tv_report_create_date);
            tvEndDate = itemView.findViewById(R.id.tv_report_due_date);
        }
    }
}
