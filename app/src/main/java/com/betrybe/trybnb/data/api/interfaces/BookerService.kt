package com.betrybe.trybnb.data.api.interfaces

import com.betrybe.trybnb.data.models.AuthLoginData
import com.betrybe.trybnb.data.models.BookingDetailData
import com.betrybe.trybnb.data.models.IdBookingData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface BookerService {

    @FormUrlEncoded
    @POST("/auth")
    suspend fun authLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<AuthLoginData>

    @GET("/booking")
    suspend fun getAllBooking(): Response<List<IdBookingData>>

    @GET("/booking/{id}")
    @Headers("Accept: application/json")
    suspend fun getBookingById(
        @Path("id") id: String
    ): Response<BookingDetailData>
}
