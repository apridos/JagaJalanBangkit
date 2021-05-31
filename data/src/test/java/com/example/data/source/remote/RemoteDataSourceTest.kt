package com.example.data.source.remote

import android.util.Log
import com.example.data.source.RemoteDataSource
import com.example.data.source.network.api.ApiService
import com.example.data.source.network.response.UsersRemote
import com.google.gson.internal.LinkedHashTreeMap
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import strikt.api.expectThat
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class RemoteDataSourceTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit
        .Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://us-central1-tensile-ship-312415.cloudfunctions.net/api/")
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    val sut = RemoteDataSource(apiService)

    @Test
    suspend fun getUsersTest(){
        mockWebServer.enqueue(MockResponse())

        val result = runBlocking(Dispatchers.IO) {
            apiService.getUsers()
        }

        assertNotNull(result)
    }
}