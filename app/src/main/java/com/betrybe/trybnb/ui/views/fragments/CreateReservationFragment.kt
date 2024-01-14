package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                binding.textSuccessReservation.visibility = View.VISIBLE
            }
        }
    }

    private fun verifyFields(): Boolean {
        var isAllFieldsValid = true

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
            val errorMessage = when (inputLayout.hint) {
                "Nome do hóspede" -> {
                    "O campo Nome é obrigatório"
                }
                "Sobrenome do hóspede" -> {
                    "O campo Sobrenome é obrigatório"
                }
                "Necessidades adicionais" -> {
                    "O campo Necessidades Adicionais é obrigatório"
                }
                "Preço total" -> {
                    "O campo Preço Total é obrigatório"
                }
                else -> {
                    "O campo ${inputLayout.hint} é obrigatório"
                }
            }
            if (text.isEmpty()) {
                validateField(inputLayout, errorMessage)
                isAllFieldsValid = false
            } else {
                validateField(inputLayout, null)
            }
        }

        return isAllFieldsValid
    }

    private fun validateField(inputLayout: TextInputLayout, errorMessage: String?) {
        inputLayout.error = errorMessage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
