package com.hampson.asta.presentation.my_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hampson.asta.databinding.FragmentMyPageBinding
import com.hampson.asta.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.constraintLayoutProfile.setOnClickListener {
            val action = MyPageFragmentDirections.actionFragmentMyPageToFragmentProfile()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}