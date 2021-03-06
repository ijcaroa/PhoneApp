package com.example.phoneapp.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "detail_list")
data class PhoneDetailEntity(
    @PrimaryKey
    @NotNull
    val id: Int,
    val description: String,
    val credit: Boolean,
    val image: String,
    val lastPrice: Int,
    val name: String,
    val price: Int
)
