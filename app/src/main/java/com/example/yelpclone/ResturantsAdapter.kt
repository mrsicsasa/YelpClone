package com.example.yelpclone

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class ResturantsAdapter(val context: Context, val restaurants: List<YelpRestaurants>):RecyclerView.Adapter<ResturantsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bind(restaurant: YelpRestaurants){
            Log.i("RestaurensAdapter","Name is ${restaurant.name}")
            itemView.findViewById<TextView>(R.id.tvName).text=restaurant.name
            itemView.findViewById<RatingBar>(R.id.ratingBar).rating=restaurant.rating.toFloat()
            itemView.findViewById<TextView>(R.id.tvNumReviews).text="${restaurant.numReviews}Reviews"
            itemView.findViewById<TextView>(R.id.tvAddress).text=restaurant.location.address
            itemView.findViewById<TextView>(R.id.tvCategory).text=restaurant.category[0].title
            itemView.findViewById<TextView>(R.id.tvDistance).text=restaurant.distanceInMetars.toString()
            itemView.findViewById<TextView>(R.id.tvPrice).text=restaurant.price
            Glide.with(context).load(restaurant.imageUrl).apply(
                RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(itemView.findViewById(R.id.imageView))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))    }

    override fun getItemCount()=restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant=restaurants[position]
        holder.bind(restaurant)
    }

}
