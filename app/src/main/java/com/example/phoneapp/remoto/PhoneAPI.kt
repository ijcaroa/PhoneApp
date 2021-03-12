package com.example.phoneapp.remoto

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneAPI {

    @GET("products/")
    suspend fun fetchPhoneList():
            Response<List<PhoneListItem>>

    @GET("details/{id}")
    suspend fun fetchDetailsPhone(@Path("id")id:Int): Response<PhoneDetailItem>
}