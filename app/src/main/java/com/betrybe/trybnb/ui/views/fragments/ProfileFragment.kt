package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentProfileBinding
import com.betrybe.trybnb.ui.viewmodels.ProfileFragmentViewModel
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        _binding?.vm = viewModel
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButtonProfile.setOnClickListener {
            if (verifyFields()) {
                val username = binding.loginInputProfile.editText?.text.toString()
                val password = binding.passwordInputProfile.editText?.text.toString()
                viewModel.authLoginBooker(username, password)
            }
        }
    }

    private fun verifyFields(): Boolean {
        var allFieldsValid = true

        val allInputFields = listOf(
            binding.loginInputProfile,
            binding.passwordInputProfile
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
