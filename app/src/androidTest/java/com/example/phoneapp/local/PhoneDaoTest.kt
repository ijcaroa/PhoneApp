package com.example.phoneapp.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.phoneapp.remoto.PhoneDetailItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhoneDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var phoneDaoTest: PhoneDao
    lateinit var detailsDao: DetailsDao
    lateinit var db: PhoneDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhoneDataBase::class.java).build()
        phoneDaoTest = db.phoneDao()
        detailsDao = db.detailsDao()
    }

    @After
    fun shutDown() {
        db.close()
    }

    @Test
    fun insertPhoneList() = runBlocking {
        //given
        val phoneListEntity = listOf(
            PhoneListEntity(1, "url", "iphone", 750000),
            PhoneListEntity(2, "url", "Samsung Galaxy", 780000)
        )
        // when
        phoneDaoTest.insertAllPhones(phoneListEntity)
        // then
        phoneDaoTest.getAllPhoneImage().observeForever {
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(2)
        }
    }

    @Test
    fun insertDetailPhone() = runBlocking {
        // given
        val phoneDetail = PhoneDetailEntity(2,
            "sdsdsdsd",
            true,
            "url",
            12500,
            "iphone",
            18500)
        val phoneID = 2
        //when
        detailsDao.insertDetailsPhone(phoneDetail)
        // then
        detailsDao.getPhoneById(phoneID).observeForever {
            assertThat(it.id).isEqualTo(2)
            assertThat(it.price).isEqualTo(18500)
        }
    }

}