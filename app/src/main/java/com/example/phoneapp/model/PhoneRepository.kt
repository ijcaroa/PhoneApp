package com.example.phoneapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.phoneapp.local.DetailsDao
import com.example.phoneapp.local.PhoneDao
import com.example.phoneapp.local.PhoneDetailEntity
import com.example.phoneapp.local.PhoneListEntity
import com.example.phoneapp.remoto.PhoneDetailItem
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
    val listAllPhone :LiveData<List<PhoneListEntity>> = phoneDao.getAllPhone()

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
    suspend fun fetchPhoneImage(id:Int){
        val service = kotlin.runCatching { retrofit.fetchDetailsPhone(id)}
        service.onSuccess {
            when(it.code()) {
                200 ->it.body()?.let {
                    detailsDao.insertDetailsPhone(converterFromInternet2(phoneDetailItem = PhoneDetailItem(id = 0,
                            description = "",credit = true,image = "",lastPrice = 0,name = "",price = 0)))
                }
                else -> Log.d("REPO-IMG", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    private fun converterFromInternet2(phoneDetailItem: PhoneDetailItem): List<PhoneDetailEntity> {
        return phoneDetailItem.id.toString().map { PhoneDetailEntity(id = 0,
                description = "",credit = true,image = "",lastPrice = 0,name = "",price = 0)  }
    }

    /*fun converterFromInternet2(phoneDetailItem: PhoneDetailItem,id: Int) : List<PhoneDetailEntity> {
        return phoneDetailItem.id.toString().map { PhoneDetailEntity(id = it.toInt()) }
    }*/

}




