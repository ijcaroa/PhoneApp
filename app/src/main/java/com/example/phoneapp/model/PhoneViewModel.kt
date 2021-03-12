package com.example.phoneapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneapp.local.PhoneDataBase
import com.example.phoneapp.local.PhoneDetailEntity
import com.example.phoneapp.local.PhoneListEntity
import kotlinx.coroutines.launch

class PhoneViewModel (application: Application): AndroidViewModel(application){

    private val repository : PhoneRepository
    val allPhones : LiveData<List<PhoneListEntity>>

    init {
        val db = PhoneDataBase.getDatabase((application))
        val phoneDao = db.phoneDao()
        val detailsDao = db.detailsDao()
        repository = PhoneRepository(phoneDao, detailsDao)
        viewModelScope.launch {
            repository.fetchImages()
        }
        allPhones = repository.listAllPhone
    }

    private var phoneSelected : Int = 0

    fun getImagesByPhoneFromInternet(id:Int) = viewModelScope.launch {
        phoneSelected = id
        repository.fetchPhoneImage(id)
    }

    fun getPhone(): LiveData<PhoneDetailEntity> = repository.getPhonebyId(phoneSelected)
}