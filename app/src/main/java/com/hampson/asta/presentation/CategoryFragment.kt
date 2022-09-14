package com.hampson.asta.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hampson.asta.databinding.CategoryFragmentBinding
import com.hampson.asta.databinding.FragmentBidBinding
import com.hampson.asta.presentation.BaseFragment

class CategoryFragment : BaseFragment() {
    private var _binding: CategoryFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CategoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}