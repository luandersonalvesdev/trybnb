package com.betrybe.trybnb.data.models

data class BookingDetailData(
    val firstname: String,
    val lastname: String,
    val totalprice: Number,
    val depositpaid: Boolean,
    val bookingdates: BookingDatesDetailData,
    val additionalneeds: String
)
