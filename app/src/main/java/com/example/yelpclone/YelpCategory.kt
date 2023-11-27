package com.example.yelpclone

import com.google.gson.annotations.SerializedName

data class YelpCategory(
    @SerializedName("title") val title:String
)
