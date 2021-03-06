package com.example.phoneapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneapp.local.PhoneDataBase
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
        allPhones = repository.listAllPhones
    }
}