package com.hampson.asta.presentation.connect.certification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentCertificationPhoneBinding
import com.hampson.asta.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertificationPhoneFragment : Fragment() {
    private var _binding: FragmentCertificationPhoneBinding? = null
    private val binding get() = _binding!!

    private val certificationViewModel by activityViewModels<CertificationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCertificationPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextChangedListener()
        setClickListener()

        collectLatestStateFlow(certificationViewModel.verifiedResult) {
            if (it.meta?.status == true) {
                certificationViewModel.saveUserSignIn(true)

                val action =
                    CertificationPhoneFragmentDirections.actionFragmentCertificationPhoneToActivityMain()
                findNavController().navigate(action)

                requireActivity().finish()
            } else if (it.meta?.status == false) {
                binding.textInputLayoutCertificationNumber.apply {
                    error = getString(R.string.error_certification_number)
                    isErrorEnabled = true
                }
            }
        }
    }

    private fun setTextChangedListener() {
        binding.editTextPhoneNumber.addTextChangedListener {
            binding.textInputLayoutPhoneNumber.isErrorEnabled = false
        }

        binding.editTextCertificationNumber.addTextChangedListener {
            binding.textInputLayoutCertificationNumber.isErrorEnabled = false
        }
    }

    private fun setClickListener() {
        binding.buttonGetCertification.setOnClickListener {
            if (!certificationViewModel.isValidationPhoneNumber(binding.editTextPhoneNumber.text.toString())) {
                binding.textInputLayoutPhoneNumber.apply {
                    error = getString(R.string.error_phone_number)
                    isErrorEnabled = true
                }

                return@setOnClickListener
            }

            val phoneNumber = binding.editTextPhoneNumber.text.toString()
            certificationViewModel.sendPhoneNumber(phoneNumber = phoneNumber)

            bindInputVerifiedNumber()
        }

        binding.buttonConfirmCertification.setOnClickListener {
            val verifiedNumber = binding.editTextCertificationNumber.text.toString()
            certificationViewModel.sendVerifiedNumber(
                token = certificationViewModel.token.value,
                verifiedNumber = verifiedNumber
            )
        }
    }

    private fun bindInputVerifiedNumber() {
        binding.textInputLayoutPhoneNumber.isEnabled = false
        binding.buttonGetCertification.isVisible = false
        binding.textInputLayoutCertificationNumber.isVisible = true
        binding.buttonConfirmCertification.isVisible = true
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}