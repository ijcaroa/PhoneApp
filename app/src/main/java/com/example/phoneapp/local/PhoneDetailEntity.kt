package com.example.phoneapp.local
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_list")
data class PhoneDetailEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    val description: String,
    val credit: Boolean,
    val image: String,
    val lastPrice: Int,
    val name: String,
    val price: Int
)
