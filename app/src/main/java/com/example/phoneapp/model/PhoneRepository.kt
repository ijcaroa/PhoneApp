package com.example.phoneapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.phoneapp.local.DetailsDao
import com.example.phoneapp.local.PhoneDao
import com.example.phoneapp.local.PhoneListEntity
import com.example.phoneapp.remoto.PhoneListItem
import com.example.phoneapp.remoto.RetrofitClient

class PhoneRepository (private val phoneDao :PhoneDao, private val detailsDao: DetailsDao) {

    private val retrofit = RetrofitClient.getRetrofit()

    suspend fun fetchImages() {
        val service = kotlin.runCatching { retrofit.fetchPhoneList() }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    phoneDao.insertAllPhones(converterFromInternet(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")

        }
    }

    fun converterFromInternet(phoneListItem: List<PhoneListItem>): List<PhoneListEntity> {
        val listPhoneEntity = mutableListOf<PhoneListEntity>()
        phoneListItem.map {
            listPhoneEntity.add(
                PhoneListEntity(
                    id = it.id,
                    image = it.image,
                    name = it.name,
                    price = it.price))
        }
        return listPhoneEntity
    }

    val listAllPhones : LiveData<List<PhoneListEntity>> = phoneDao.getAllPhone()
}



