package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.google.android.material.textfield.TextInputLayout

class CreateReservationFragment : Fragment(R.layout.fragment_create_reservation) {
    private var _binding: FragmentCreateReservationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateReservationBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createReservationButton.setOnClickListener {
            if (verifyFields()) {
                Toast.makeText(context, "REQUISITANDO", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifyFields(): Boolean {
        var allFieldsValid = true

        val allInputFields = listOf(
            binding.firstNameCreateReservation,
            binding.lastNameCreateReservation,
            binding.checkinCreateReservation,
            binding.checkoutCreateReservation,
            binding.additionalNeedsCreateReservation,
            binding.totalPriceCreateReservation
        )

        allInputFields.forEach { inputLayout ->
            val text = inputLayout.editText?.text.toString().trim()
            val errorMessage = "O campo ${inputLayout.hint} é obrigatório"
            if (text.isEmpty()) {
                validateField(inputLayout, errorMessage)
                allFieldsValid = false
            } else {
                validateField(inputLayout, null)
            }
        }

        return allFieldsValid
    }

    private fun validateField(inputLayout: TextInputLayout, errorMessage: String?) {
        inputLayout.error = errorMessage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
