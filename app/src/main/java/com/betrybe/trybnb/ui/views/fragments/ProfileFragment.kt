package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentProfileBinding
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButtonProfile.setOnClickListener {
            verifyFields()
        }

        binding.passwordInputProfile.editText?.doOnTextChanged { _, _, _, _ ->
            verifyFields()
        }
    }

    private fun verifyFields() {
        val loginText = binding.loginInputProfile.editText?.text.toString().trim()
        val passwordText = binding.passwordInputProfile.editText?.text.toString().trim()

        validateField(
            binding.loginInputProfile,
            loginText,
            getString(R.string.input_login_error_message)
        )

        validateField(
            binding.passwordInputProfile,
            passwordText,
            getString(R.string.input_password_error_message)
        )
    }

    private fun validateField(inputLayout: TextInputLayout, text: String, errorMessage: String) {
        inputLayout.error = if (text.isEmpty()) errorMessage else null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
