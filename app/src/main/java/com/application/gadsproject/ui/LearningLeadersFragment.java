package com.application.gadsproject.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.application.gadsproject.R;
import com.application.gadsproject.adapters.LearningLeadersViewHolder;
import com.application.gadsproject.models.LearningLeadersModel;
import com.application.gadsproject.services.LearningLeadersService;
import com.application.gadsproject.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;


public class LearningLeadersFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        LearningLeadersService taskService = ServiceBuilder.buildService(LearningLeadersService.class);
        Call<List<LearningLeadersModel>> call = taskService.getLearningLeaders();

        call.enqueue(new Callback<List<LearningLeadersModel>>() {
            @Override
            public void onResponse(Call<List<LearningLeadersModel>> call, Response<List<LearningLeadersModel>> response) {

                List<LearningLeadersModel> list = response.body();
                LearningLeadersViewHolder viewHolder = new LearningLeadersViewHolder(getContext(), list);
                recyclerView.setAdapter(viewHolder);
            }

            @Override
            public void onFailure(Call<List<LearningLeadersModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });


        return root;
    }
}