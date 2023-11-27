package com.example.yelpclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL="https://api.yelp.com/v3/"
private const val API_KEY="NocSovz5DKddKOr_HN4adgD2jIg-A2RMWGLy7dvSBW-E8wmX3n5yx4TN4-MvBRscPyAPiEy2R4QpmK6yqny91qDd-xO0CFgDmzznqoaNZTonKx_AfmWGq12QQtlkZXYx"
class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            val yelpService=retrofit.create(YelpService::class.java)
            yelpService.searchRestourants("Bearer $API_KEY","Avocado Toast","New York").enqueue(object:Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Log.i("MainActivity","onResponse $response")
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.i("MainActivity","onResponse $t")
                }
            })
    }
}