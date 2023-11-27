package com.example.yelpclone

import com.google.gson.annotations.SerializedName

data class YelpLocation(
    @SerializedName("address1") val address:String
)
