package com.betrybe.trybnb.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.BookingDetailData

class BookingAdapter(
    private val bookingList: List<BookingDetailData?>
) : Adapter<BookingAdapter.BookingViewHolder>() {

    class BookingViewHolder(view: View) : ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_item_reservation)
        val checkin: TextView = view.findViewById(R.id.checkin_item_reservation)
        val checkout: TextView = view.findViewById(R.id.checkout_item_reservation)
        val additionalNeeds: TextView = view.findViewById(R.id.additional_needs_item_reservation)
        val totalPrice: TextView = view.findViewById(R.id.total_price_item_reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reservation, parent, false)
        return BookingViewHolder(view)
    }

    override fun getItemCount(): Int = bookingList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.name.text = "${bookingList[position]?.firstname} ${bookingList[position]?.lastname}"
        holder.checkin.text = bookingList[position]?.bookingdates?.checkin
        holder.checkout.text = bookingList[position]?.bookingdates?.checkout
        holder.additionalNeeds.text = bookingList[position]?.additionalneeds
        holder.totalPrice.text = bookingList[position]?.totalprice.toString()
    }
}
