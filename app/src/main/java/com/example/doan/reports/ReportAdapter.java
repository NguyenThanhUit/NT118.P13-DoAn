package com.example.doan.reports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.doan.R;


public class ReportAdapter extends ListAdapter<Reports, ReportAdapter.ReportViewHolder> {

    private OnItemClickListener listener;

    public ReportAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Reports> DIFF_CALLBACK = new DiffUtil.ItemCallback<Reports>() {
        @Override
        public boolean areItemsTheSame(@NonNull Reports oldItem, @NonNull Reports newItem) {
            return oldItem.getReportID().equals(newItem.getReportID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Reports oldItem, @NonNull Reports newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reports, parent, false);
        return new ReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Reports currentReport = getItem(position);
        holder.tvTitle.setText(currentReport.getReportTitle());
        holder.tvStartDate.setText(currentReport.getStartDate());
        holder.tvEndDate.setText(currentReport.getEndDate());
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvStartDate, tvEndDate;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_report_title);
            tvStartDate = itemView.findViewById(R.id.tv_report_create_date);
            tvEndDate = itemView.findViewById(R.id.tv_report_due_date);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Reports report);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
