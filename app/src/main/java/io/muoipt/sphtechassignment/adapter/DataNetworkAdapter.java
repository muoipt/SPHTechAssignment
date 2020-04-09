package io.muoipt.sphtechassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.muoipt.sphtechassignment.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.muoipt.sphtechassignment.data.model.Quarter;
import io.muoipt.sphtechassignment.data.model.Year;
import io.muoipt.sphtechassignment.utils.AnimationUtils;

public class DataNetworkAdapter extends RecyclerView.Adapter<DataNetworkAdapter.DataNetworkViewHolder> {

    private Context context;
    private List<Year> yearList = new ArrayList<>();

    public DataNetworkAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DataNetworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_network_data, parent, false);

        return new DataNetworkViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull DataNetworkViewHolder holder, int position) {

        Year year = yearList.get(position);

        holder.textViewYear.setText(String.valueOf(year.getYear()));

        if (year.isYearCompleted())
            holder.textViewSent.setText(String.valueOf(year.getTotalSent()));
        else
            holder.textViewSent.setText(String.valueOf(year.getTotalSent()) + "*");

        if (!year.isDecreasedGrowth())
            holder.imageButtonDetailedSent.setVisibility(View.GONE);
        else {
            holder.imageButtonDetailedSent.setVisibility(View.VISIBLE);
            holder.imageButtonDetailedSent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.linearLayoutQuarterDetailsContainer.getVisibility() == View.VISIBLE) {
                        AnimationUtils.collapse(holder.linearLayoutQuarterDetailsContainer);
                        holder.imageButtonDetailedSent.setBackground(context.getResources().getDrawable(R.drawable.ic_arrow_drop_down_36dp));
                    } else {
                        AnimationUtils.expand(holder.linearLayoutQuarterDetailsContainer);
                        holder.imageButtonDetailedSent.setBackground(context.getResources().getDrawable(R.drawable.ic_arrow_drop_up_36dp));
                    }
                }
            });
        }

        initQuarterDetails(holder, year.getQuarters());
    }

    public void initQuarterDetails(DataNetworkViewHolder holder, List<Quarter> quarters) {

        if (quarters.size() >= 1) {
            holder.textViewQuarter1Value.setText(String.valueOf(quarters.get(0).getSent()));

            if (quarters.get(0).getSentGrowth() >= 0) {
                holder.textViewQuarter1.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter1Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter1.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter1Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter1.setVisibility(View.GONE);
            holder.textViewQuarter1Value.setVisibility(View.GONE);
        }

        if (quarters.size() >= 2) {
            holder.textViewQuarter2Value.setText(String.valueOf(quarters.get(1).getSent()));

            if (quarters.get(1).getSentGrowth() >= 0) {
                holder.textViewQuarter2.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter2Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter2.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter2Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter2.setVisibility(View.GONE);
            holder.textViewQuarter2Value.setVisibility(View.GONE);
        }

        if (quarters.size() >= 3) {
            holder.textViewQuarter3Value.setText(String.valueOf(quarters.get(2).getSent()));

            if (quarters.get(2).getSentGrowth() >= 0) {
                holder.textViewQuarter3.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter3Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter3.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter3Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter3.setVisibility(View.GONE);
            holder.textViewQuarter3Value.setVisibility(View.GONE);
        }

        if (quarters.size() >= 4) {
            holder.textViewQuarter4Value.setText(String.valueOf(quarters.get(3).getSent()));

            if (quarters.get(3).getSentGrowth() >= 0) {
                holder.textViewQuarter4.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter4Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter4.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter4Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter4.setVisibility(View.GONE);
            holder.textViewQuarter4Value.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return yearList.size();
    }

    public void setYearList(List<Year> yearList) {
        this.yearList = yearList;
    }

    public class DataNetworkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvYear)
        TextView textViewYear;

        @BindView(R.id.tvSent)
        TextView textViewSent;

        @BindView(R.id.iBtnDetailedSent)
        ImageButton imageButtonDetailedSent;

        @BindView(R.id.llQuarterDetailsContainer)
        LinearLayout linearLayoutQuarterDetailsContainer;

        @BindView(R.id.tvQuarter1)
        TextView textViewQuarter1;

        @BindView(R.id.tvQuarter1Value)
        TextView textViewQuarter1Value;

        @BindView(R.id.tvQuarter2)
        TextView textViewQuarter2;

        @BindView(R.id.tvQuarter2Value)
        TextView textViewQuarter2Value;

        @BindView(R.id.tvQuarter3)
        TextView textViewQuarter3;

        @BindView(R.id.tvQuarter3Value)
        TextView textViewQuarter3Value;

        @BindView(R.id.tvQuarter4)
        TextView textViewQuarter4;

        @BindView(R.id.tvQuarter4Value)
        TextView textViewQuarter4Value;

        public DataNetworkViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
