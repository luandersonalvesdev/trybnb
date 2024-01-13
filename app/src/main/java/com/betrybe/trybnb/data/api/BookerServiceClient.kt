package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.api.interfaces.BookerService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookerServiceClient {

    private const val BASE_URL = "https://restful-booker.herokuapp.com/"

    val instance: BookerService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BookerService::class.java)
    }
}
