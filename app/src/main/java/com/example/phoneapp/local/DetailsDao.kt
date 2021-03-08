package com.example.phoneapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailsPhone (detailsPhone: kotlin.collections.List<com.example.phoneapp.local.PhoneDetailEntity>)



    @Query("SELECT * FROM detail_list ORDER BY id DESC")
    fun getAllDetails(): LiveData<List<PhoneDetailEntity>>

    @Query("SELECT * FROM detail_list WHERE id = :id")
    fun getPhoneById (id: Int) : LiveData<PhoneDetailEntity>

}