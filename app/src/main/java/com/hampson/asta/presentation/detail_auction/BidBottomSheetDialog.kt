package com.hampson.asta.presentation.detail_auction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hampson.asta.databinding.BottomSheetDialogBidBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class BidBottomSheetDialog :
    BottomSheetDialogFragment() {

    private val increase = 10000
    private var currentPay = 260000
    private val dec = DecimalFormat("#,###")

    private var _binding: BottomSheetDialogBidBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDialogBidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListener()
    }

    private fun setupClickListener() {
        binding.imageViewMinus.setOnClickListener {
            if (currentPay <= 260000) {
                Toast.makeText(context, "현재가보다 높게 설정해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            currentPay -= increase
            bindBidPrice()
        }

        binding.imageViewPlus.setOnClickListener {
            currentPay += increase
            bindBidPrice()
        }
    }

    private fun bindBidPrice() {
        binding.textViewBidPrice.text = "${dec.format(currentPay)}원"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}