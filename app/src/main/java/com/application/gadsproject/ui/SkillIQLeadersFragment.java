package com.application.gadsproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.gadsproject.R;
import com.application.gadsproject.adapters.SkillIQViewHolder;
import com.application.gadsproject.models.SkillIQModel;
import com.application.gadsproject.services.ServiceBuilder;
import com.application.gadsproject.services.SkillIQService;

import java.util.List;


public class SkillIQLeadersFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View root = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);

       recyclerView = root.findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        SkillIQService service = ServiceBuilder.buildService(SkillIQService.class);
        Call<List<SkillIQModel>> call = service.getSkillIQLeaders();

        call.enqueue(new Callback<List<SkillIQModel>>() {
            @Override
            public void onResponse(Call<List<SkillIQModel>> call, Response<List<SkillIQModel>> response) {
                List<SkillIQModel> list = response.body();
                SkillIQViewHolder viewHolder = new SkillIQViewHolder(list, getContext());
                recyclerView.setAdapter(viewHolder);
            }

            @Override
            public void onFailure(Call<List<SkillIQModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });






      return root;
    }
}