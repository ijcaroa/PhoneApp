package com.example.phoneapp.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "phones_list")
data class PhoneListEntity(
    @PrimaryKey
    @NotNull
    val id: Int,
    val image: String,
    val name: String,
    val price: Int

)
