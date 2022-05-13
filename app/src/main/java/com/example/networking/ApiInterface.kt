package com.example.networking

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

interface APIServiceInterface {
    @GET
    suspend fun getResponse(@Url url: String): Response<ResponseClass>
}
