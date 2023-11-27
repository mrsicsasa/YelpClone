    package com.example.yelpclone

    import com.google.gson.annotations.SerializedName

    data class YelpRestaurants(
        @SerializedName("name") val name:String,
        @SerializedName("rating") val rating:Double,
        @SerializedName("price") val price:String,
        @SerializedName("review_count") val numReviews:Int,
        @SerializedName("distance") val distanceInMetars:Double,
        @SerializedName("image_url") val imageUrl:String,
        @SerializedName("categories") val category: List<YelpCategory>,
        @SerializedName("location") val location: YelpLocation,
    )
