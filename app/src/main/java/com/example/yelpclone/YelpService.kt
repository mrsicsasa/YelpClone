package com.example.yelpclone

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpService {
    @GET("businesses/search")
    fun searchRestourants(
        @Header("Authorization") authHeader:String,
        @Query("term") searchTerm:String,
        @Query("location") location:String
    ):retrofit2.Call<Any>
}