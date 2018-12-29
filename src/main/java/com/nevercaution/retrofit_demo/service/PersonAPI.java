package com.nevercaution.retrofit_demo.service;

import com.nevercaution.retrofit_demo.model.Person;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PersonAPI {

    @GET("/persons")
    Call<List<Person>> getPersonList();

    @POST("/person")
    Call<Person> getPerson(@Query(value = "name") String name);
}
