package com.application.gadsproject.services;

import com.application.gadsproject.models.LearningLeadersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningLeadersService {

    @GET("/api/hours")
    Call<List<LearningLeadersModel>> getLearningLeaders();

}
