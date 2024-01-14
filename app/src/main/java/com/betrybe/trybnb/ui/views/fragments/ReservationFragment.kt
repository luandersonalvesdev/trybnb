package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentReservationBinding
import com.betrybe.trybnb.ui.adapters.BookingAdapter
import com.betrybe.trybnb.ui.viewmodels.ReservationFragmentViewModel

class ReservationFragment : Fragment(R.layout.fragment_reservation) {
    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReservationFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)

        val recyclerView = binding.reservationRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getBookingDetailsList()

        viewModel.bookingList.observe(viewLifecycleOwner) { updatedBookingList ->
            recyclerView.adapter = BookingAdapter(updatedBookingList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
