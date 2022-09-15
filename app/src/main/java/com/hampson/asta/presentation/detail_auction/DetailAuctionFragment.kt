package com.hampson.asta.presentation.detail_auction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hampson.asta.databinding.FragmentDetailAuctionBinding
import com.hampson.asta.presentation.BaseFragment

class DetailAuctionFragment : BaseFragment() {
    private var _binding: FragmentDetailAuctionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAuctionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}