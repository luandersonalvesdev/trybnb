package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.BookerServiceClient
import com.betrybe.trybnb.data.models.BookingDetailData
import com.betrybe.trybnb.data.models.IdBookingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val MAX_ID_BOOKINGS = 5
class ReservationFragmentViewModel : ViewModel() {

    private val bookerService = BookerServiceClient.instance
    private var _bookingList = MutableLiveData<List<BookingDetailData?>>()
    val bookingList: LiveData<List<BookingDetailData?>>
        get() = _bookingList

    private suspend fun getAllBooking(): List<IdBookingData>? {
        val responseIdBookings = bookerService.getAllBooking()
        return responseIdBookings.body()
    }

    private suspend fun getBookingById(id: String): BookingDetailData? {
        val responseBookingDetail = bookerService.getBookingById(id)
        return responseBookingDetail.body()
    }

    fun getBookingDetailsList() {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val responseIdBookings = getAllBooking()
            if (responseIdBookings !== null) {
                val firstsIdBooking = responseIdBookings.take(MAX_ID_BOOKINGS)

                val bookingDetailList = firstsIdBooking.map {
                    getBookingById(it.bookingid.toString())
                }

                _bookingList.postValue(bookingDetailList)
            }
            ApiIdlingResource.decrement()
        }
    }
}
