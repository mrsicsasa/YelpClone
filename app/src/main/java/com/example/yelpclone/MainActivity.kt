package com.example.yelpclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY =
    "NocSovz5DKddKOr_HN4adgD2jIg-A2RMWGLy7dvSBW-E8wmX3n5yx4TN4-MvBRscPyAPiEy2R4QpmK6yqny91qDd-xO0CFgDmzznqoaNZTonKx_AfmWGq12QQtlkZXYx"

class MainActivity : AppCompatActivity() {
    private lateinit var rvRestaurants: RecyclerView
    val restaurants = mutableListOf<YelpRestaurants>()
    private lateinit var adapter: ResturantsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        val retrofit=createRetrofit()
        val yelpService=retrofit.create(YelpService::class.java)
        searchYelpRestaurants(yelpService)
    }

    private fun setupRecyclerView() {
        adapter= ResturantsAdapter(this,restaurants)
        rvRestaurants=findViewById(R.id.rvRestaurants)
        rvRestaurants.adapter=adapter
        rvRestaurants.layoutManager=LinearLayoutManager(this)
    }
    private fun createRetrofit()=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    private fun searchYelpRestaurants(yelpService: YelpService) {
        yelpService.searchRestourants("Bearer $API_KEY","Avocado Toast","New York").enqueue(object :Callback<YelpSearchResult>{
            override fun onResponse(
                call: Call<YelpSearchResult>,
                response: Response<YelpSearchResult>
            ) {
                handleApiResponse(response)
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.e("MainActivity", "onFailure", t)
            }
        })


    }

    private fun handleApiResponse(response: Response<YelpSearchResult>) {
        val body=response.body()
        if(body==null){
            Log.w("MainActivity", "Did not receive valid response body from Yelp API")
            return
        }
        restaurants.addAll(body.restaurants)
        adapter.notifyDataSetChanged()
    }


}