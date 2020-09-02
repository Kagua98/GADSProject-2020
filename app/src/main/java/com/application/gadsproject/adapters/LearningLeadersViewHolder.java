package com.application.gadsproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.gadsproject.models.LearningLeadersModel;
import com.application.gadsproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearningLeadersViewHolder extends RecyclerView.Adapter<LearningLeadersViewHolder.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    List<LearningLeadersModel> list;

    public LearningLeadersViewHolder(Context c, List<LearningLeadersModel> list) {
        this.context = c;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LearningLeadersViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.learning_leaders_child, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersViewHolder.ViewHolder holder, int position) {
        LearningLeadersModel leadersModel = list.get(position);
        holder.names.setText(leadersModel.getName());
        holder.hours.setText(String.valueOf(leadersModel.getHours()) + " learning hours, ");
        holder.country.setText(leadersModel.getCountry() + ".");
        Picasso.get().load(leadersModel.getBadgeUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView names, hours, country;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            names = itemView.findViewById(R.id.names);
            hours = itemView.findViewById(R.id.hours);
            country = itemView.findViewById(R.id.country);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
