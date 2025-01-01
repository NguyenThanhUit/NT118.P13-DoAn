package com.example.doan.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.customers.CustomerViewModel;
import com.example.doan.domain.employee.EmployeeDetails;
import com.example.doan.domain.employee.TopEmployeesAdapter;
import com.example.doan.orders.OrdersViewModel;
import com.example.doan.tasks.TasksViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.example.doan.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdminStatisticFragment extends Fragment {

    private PieChart semiDonutChart;
    private PieChart customerSegmentationPieChart;
    private LinearLayout semiDonutLegend;
    private LinearLayout customerSegmentationLegend;
    private CustomerViewModel customerViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View rootView = inflater.inflate(R.layout.admin_statistic, container, false);

        // Initialize charts
        semiDonutChart = rootView.findViewById(R.id.semi_donut_chart);
        customerSegmentationPieChart = rootView.findViewById(R.id.customer_segmentation_pie_chart);

        // Initialize legends
        semiDonutLegend = rootView.findViewById(R.id.semi_donut_legend);
        customerSegmentationLegend = rootView.findViewById(R.id.customer_segmentation_legend);

        // Initialize ViewModel
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        // Update New Opportunities
        updateNewOpportunities(rootView);

        // Update Revenue Generated
        updateRevenueGenerated(rootView);

        // Cập nhật Sales Goal
        updateSalesGoal(rootView);

        // Update Won Opportunities
        updateWonOpportunities(rootView);

        // Set up the charts
        setupCustomerSegmentationPieChart(rootView);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_top_employees);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TopEmployeesAdapter adapter = new TopEmployeesAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        OrdersViewModel ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        ordersViewModel.getTopEmployeesWithDetails().observe(getViewLifecycleOwner(), topEmployees -> {
            adapter.setEmployees(topEmployees);
        });

        ordersViewModel.getTopEmployeesWithDetails().observe(getViewLifecycleOwner(), topEmployees -> {
            for (EmployeeDetails employee : topEmployees) {
                Log.d("TopEmployees", "Name: " + employee.employeeName +
                        ", Phone: " + employee.employeePhone +
                        ", Email: " + employee.employeeEmail +
                        ", Total Order: " + employee.totalOrder);
            }
            adapter.setEmployees(topEmployees);
        });

        return rootView;
    }

    private void updateWonOpportunities(View rootView) {
        TextView wonOpportunitiesValue = rootView.findViewById(R.id.won_opportunities_percentage);

        TasksViewModel tasksViewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        tasksViewModel.getTotalTaskCount().observe(getViewLifecycleOwner(), totalTasks -> {
            if (totalTasks != null && totalTasks > 0) {
                tasksViewModel.getCompletedTaskCount().observe(getViewLifecycleOwner(), completedTasks -> {
                    if (completedTasks != null) {
                        float percentage = (float) completedTasks / totalTasks * 100;
                        String formattedPercentage = String.format("%.2f", percentage) + "%";
                        wonOpportunitiesValue.setText(formattedPercentage);
                    } else {
                        wonOpportunitiesValue.setText("0%");
                    }
                });
            } else {
                wonOpportunitiesValue.setText("0%");
            }
        });
    }

    private void updateSalesGoal(View rootView) {
        PieChart salesGoalChart = rootView.findViewById(R.id.semi_donut_chart);

        OrdersViewModel ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        ordersViewModel.getTotalOrderRevenue().observe(getViewLifecycleOwner(), totalRevenue -> {
            if (totalRevenue != null) {
                float goal = 10_000_000f; // 10 triệu
                float achieved = Math.min(totalRevenue, goal);
                float remaining = goal - achieved;

                // Cập nhật dữ liệu biểu đồ
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(achieved, "")); // Không có label
                entries.add(new PieEntry(remaining, "")); // Không có label

                PieDataSet dataSet = new PieDataSet(entries, "");
                dataSet.setColors(new int[]{
                        android.graphics.Color.parseColor("#4CAF50"), // Xanh lá cây
                        android.graphics.Color.parseColor("#BDBDBD")  // Xám
                });
                dataSet.setDrawValues(false);

                PieData data = new PieData(dataSet);
                salesGoalChart.setData(data);

                // Tùy chỉnh hiển thị biểu đồ
                salesGoalChart.setHoleRadius(80f);
                salesGoalChart.setTransparentCircleRadius(85f);
                salesGoalChart.setRotationAngle(270f); // Semi-donut
                salesGoalChart.setDrawHoleEnabled(true);
                salesGoalChart.setCenterText(formatCurrency(achieved) + " / " + formatCurrency(goal));
                salesGoalChart.setCenterTextSize(16f);

                // Tắt các thành phần không mong muốn
                salesGoalChart.getLegend().setEnabled(false); // Tắt Legend
                salesGoalChart.getDescription().setEnabled(false); // Tắt Description Label

                salesGoalChart.invalidate();
            }
        });

        // Add legend items
        addLegendItem(semiDonutLegend, "Achieved", android.graphics.Color.parseColor("#4CAF50"));
        addLegendItem(semiDonutLegend, "Remaining", android.graphics.Color.parseColor("#BDBDBD"));
    }


    private void updateNewOpportunities(View rootView) {
        TextView newOpportunitiesValue = rootView.findViewById(R.id.new_opportunities_value);

        customerViewModel.getCustomerCountByCategory("Nóng").observe(getViewLifecycleOwner(), hotCustomers -> {
            customerViewModel.getCustomerCountByCategory("Tiềm năng").observe(getViewLifecycleOwner(), potentialCustomers -> {
                int totalNewOpportunities = (hotCustomers != null ? hotCustomers : 0) +
                        (potentialCustomers != null ? potentialCustomers : 0);
                newOpportunitiesValue.setText(String.valueOf(totalNewOpportunities));
            });
        });
    }

    private void updateRevenueGenerated(View rootView) {
        TextView revenueGeneratedValue = rootView.findViewById(R.id.revenue_generated_value);

        OrdersViewModel ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        ordersViewModel.getTotalOrderRevenue().observe(getViewLifecycleOwner(), totalRevenue -> {
            if (totalRevenue != null) {
                String formattedRevenue = formatCurrency(totalRevenue);
                revenueGeneratedValue.setText(formattedRevenue);
            } else {
                revenueGeneratedValue.setText("0đ");
            }
        });
    }

    private String formatCurrency(float amount) {
        if (amount >= 1_000_000_000) {
            return String.format("%.2f", amount / 1_000_000_000).replaceAll("\\.0+$", "") + "t"; // Tỷ
        } else if (amount >= 1_000_000) {
            return String.format("%.2f", amount / 1_000_000).replaceAll("\\.0+$", "") + "tr"; // Triệu
        } else if (amount >= 1_000) {
            return String.format("%.2f", amount / 1_000).replaceAll("\\.0+$", "") + "k"; // Nghìn
        } else {
            return new DecimalFormat("#,###").format(amount) + "đ"; // Đồng
        }
    }

    private void setupCustomerSegmentationPieChart(View rootView) {
        customerViewModel.getTotalCustomerCount().observe(getViewLifecycleOwner(), totalCustomers -> {
            if (totalCustomers == null || totalCustomers == 0) return;

            customerViewModel.getCustomerCountByCategory("Mới").observe(getViewLifecycleOwner(), newCustomers -> {
                customerViewModel.getCustomerCountByCategory("Chưa tiếp cận").observe(getViewLifecycleOwner(), notContactedCustomers -> {
                    customerViewModel.getCustomerCountByCategory("Tiếp cận").observe(getViewLifecycleOwner(), contactedCustomers -> {
                        customerViewModel.getCustomerCountByCategory("Nóng").observe(getViewLifecycleOwner(), hotCustomers -> {
                            customerViewModel.getCustomerCountByCategory("Tiềm năng").observe(getViewLifecycleOwner(), potentialCustomers -> {
                                ArrayList<PieEntry> entries = new ArrayList<>();

                                if (newCustomers != null && newCustomers > 0)
                                    entries.add(new PieEntry((float) newCustomers / totalCustomers * 100));
                                if (notContactedCustomers != null && notContactedCustomers > 0)
                                    entries.add(new PieEntry((float) notContactedCustomers / totalCustomers * 100));
                                if (contactedCustomers != null && contactedCustomers > 0)
                                    entries.add(new PieEntry((float) contactedCustomers / totalCustomers * 100));
                                if (hotCustomers != null && hotCustomers > 0)
                                    entries.add(new PieEntry((float) hotCustomers / totalCustomers * 100));
                                if (potentialCustomers != null && potentialCustomers > 0)
                                    entries.add(new PieEntry((float) potentialCustomers / totalCustomers * 100));

                                PieDataSet dataSet = new PieDataSet(entries, "");
                                dataSet.setColors(new int[]{
                                        android.graphics.Color.parseColor("#4FC3F7"),
                                        android.graphics.Color.parseColor("#BA68C8"),
                                        android.graphics.Color.parseColor("#FFD54F"),
                                        android.graphics.Color.parseColor("#81C784"),
                                        android.graphics.Color.parseColor("#E57373")
                                });
                                dataSet.setValueTextSize(12f);
                                dataSet.setValueTextColor(android.graphics.Color.BLACK);
                                dataSet.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> String.format("%.0f%%", value));

                                PieData data = new PieData(dataSet);
                                customerSegmentationPieChart.setData(data);

                                customerSegmentationPieChart.setDrawHoleEnabled(true);
                                customerSegmentationPieChart.setHoleRadius(50f);
                                customerSegmentationPieChart.setTransparentCircleRadius(55f);
                                customerSegmentationPieChart.setDescription(null);
                                customerSegmentationPieChart.getLegend().setEnabled(false);

                                addLegendItem(customerSegmentationLegend, "New", android.graphics.Color.parseColor("#4FC3F7"));
                                addLegendItem(customerSegmentationLegend, "Not Contacted", android.graphics.Color.parseColor("#BA68C8"));
                                addLegendItem(customerSegmentationLegend, "Contacted", android.graphics.Color.parseColor("#FFD54F"));
                                addLegendItem(customerSegmentationLegend, "Hot", android.graphics.Color.parseColor("#81C784"));
                                addLegendItem(customerSegmentationLegend, "Potential", android.graphics.Color.parseColor("#E57373"));

                                customerSegmentationPieChart.invalidate();
                            });
                        });
                    });
                });
            });
        });
    }

    private void addLegendItem(LinearLayout legendContainer, String label, int color) {
        View legendItem = LayoutInflater.from(getContext()).inflate(R.layout.legend_item, legendContainer, false);
        View colorIndicator = legendItem.findViewById(R.id.color_indicator);
        TextView categoryLabel = legendItem.findViewById(R.id.category_label);

        colorIndicator.setBackgroundColor(color);
        categoryLabel.setText(label);

        legendContainer.addView(legendItem);
    }
}
