package com.example.phoneapp.remoto

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhoneApi {

    lateinit var mockWebServer: MockWebServer
    lateinit var phoneAPI: PhoneAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        val mRetrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        phoneAPI = mRetrofit.create(PhoneAPI::class.java)
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun happy_case_fetch_phone() = runBlocking {
        //given
        val phoneListItem = listOf(PhoneListItem(1, "url", "iphone", 750000),
            PhoneListItem(2, "url", "Samsung Galaxy", 780000))
        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(phoneListItem)))
        //when
        val result = phoneAPI.fetchPhoneList()
        //then
        val body = result.body()

        assertThat(result).isNotNull()
        //assertThat(result).isNull()
        if (body != null) {
            assertThat(body.size).isEqualTo(2)
        }

        val path = mockWebServer.takeRequest()
        println(path.path)
        assertThat(path.path).isEqualTo("/products/")


    }

    @Test
    fun detail_from_internet() = runBlocking {
        // given
        val phoneDetail = PhoneDetailItem(true,
            "sdsdsdsd",
            2, "url",
            12500, "iphone",
            18500)
        val phoneID = 2
        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(phoneDetail)))
        // When
        val result = phoneAPI.fetchDetailsPhone(phoneID)
        //then
        val body = result.body()

        if (body != null) {
            assertThat(body.lastPrice).isEqualTo(12500)
            assertThat(body.description).isEqualTo("sdsdsdsd")
        }
    }



}