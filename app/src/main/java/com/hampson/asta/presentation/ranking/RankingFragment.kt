package com.hampson.asta.presentation.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hampson.asta.databinding.FragmentRankingBinding
import com.hampson.asta.presentation.BaseFragment

class RankingFragment : BaseFragment() {
    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    override var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}