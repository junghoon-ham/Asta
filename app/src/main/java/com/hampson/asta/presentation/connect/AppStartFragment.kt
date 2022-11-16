package com.hampson.asta.presentation.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hampson.asta.databinding.FragmentAppStartBinding
import com.hampson.asta.presentation.connect.certification.CertificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AppStartFragment : Fragment() {
    private var _binding: FragmentAppStartBinding? = null
    private val binding get() = _binding!!

    private val certificationViewModel by activityViewModels<CertificationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkUserSignIn()

        super.onViewCreated(view, savedInstanceState)

        setupClickListener()
    }

    private fun checkUserSignIn() {
        lifecycleScope.launch {
            //if (certificationViewModel.getUserSignIn()) {
            val action =
                AppStartFragmentDirections.actionFragmentAppStartToActivityMain()
            findNavController().navigate(action)

            requireActivity().finish()
            //}
        }
    }

    private fun setupClickListener() {
        binding.buttonLogin.setOnClickListener {
            val action =
                AppStartFragmentDirections.actionFragmentAppStartToFragmentCertificationPhone()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}