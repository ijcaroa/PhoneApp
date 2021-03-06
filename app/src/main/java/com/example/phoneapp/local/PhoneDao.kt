package com.example.phoneapp.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(bookList: List<PhoneListEntity>)

    @Update
    suspend fun updateFavImages(phoneEntity: PhoneListEntity)

    @Query("SELECT * FROM phones_list ORDER BY image ASC")
    fun getAllPhoneImage(): LiveData<List<PhoneListEntity>>

    @Query("SELECT * FROM phones_list ORDER BY id DESC")
    fun getAllPhone(): LiveData<List<PhoneListEntity>>

}


