package com.androappsolution.myvideomaker.rest;

import com.androappsolution.myvideomaker.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("SignedUrl")
    Call<MoviesResponse> getTopRatedMovies(@Field("Status") String image_id);


}
