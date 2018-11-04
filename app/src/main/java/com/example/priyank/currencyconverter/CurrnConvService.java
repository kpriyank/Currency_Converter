package com.example.priyank.currencyconverter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

interface CurrnConvService {
    @GET("/exchange")
    Call<String>Currency(@Header("X-Mashape-Key")String header, @Query("from")String number,@Query("q") String value,@Query("to") String currTo);
}
