package com.example.phoneapp.remoto


import com.google.gson.annotations.SerializedName

data class PhoneListItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)