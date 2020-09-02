package com.application.gadsproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.gadsproject.R;
import com.application.gadsproject.models.SkillIQModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillIQViewHolder extends RecyclerView.Adapter<SkillIQViewHolder.ViewHolder> {

    List<SkillIQModel> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public SkillIQViewHolder(List<SkillIQModel> list, Context c) {
        this.list = list;
        this.context = c;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SkillIQViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.skill_iq_child, parent, false);
        return new SkillIQViewHolder.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQViewHolder.ViewHolder holder, int position) {
        SkillIQModel model = list.get(position);
        holder.names.setText(model.getName());
        holder.score.setText(String.valueOf(model.getScore()) + " skill IQ Score, ");
        holder.country.setText(model.getCountry());
        Picasso.get().load(model.getBadgeUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView names, score, country;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            names = itemView.findViewById(R.id.names);
            score = itemView.findViewById(R.id.score);
            country = itemView.findViewById(R.id.country);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
