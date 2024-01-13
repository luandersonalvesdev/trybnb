package com.betrybe.trybnb.data.api.interfaces

import com.betrybe.trybnb.data.models.AuthLoginData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BookerService {

    @FormUrlEncoded
    @POST("/auth")
    suspend fun authLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<AuthLoginData>
}
