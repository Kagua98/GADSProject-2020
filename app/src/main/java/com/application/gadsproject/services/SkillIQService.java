package com.application.gadsproject.services;

import com.application.gadsproject.models.SkillIQModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIQService {
    @GET("/api/skilliq")
    Call<List<SkillIQModel>> getSkillIQLeaders();

}
