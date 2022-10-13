package com.hampson.asta.presentation.register_auction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.hampson.asta.R
import com.hampson.asta.databinding.BottomSheetDialogRegisterPriceBinding
import com.hampson.asta.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceBottomSheetDialog(
    private val priceType: Product.PriceType,
    private val priceInfoList: ArrayList<String>
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogRegisterPriceBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<RegisterAuctionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDialogRegisterPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindTitle()
        bindInfo()
        setupClickListener()
    }

    private fun bindTitle() {
        binding.textViewTitle.text = when (priceType) {
            Product.PriceType.START -> getString(R.string.price_start)
            Product.PriceType.HOPE -> getString(R.string.price_hope)
            Product.PriceType.INCREASE -> getString(R.string.price_increase)
            else -> ""
        }
    }

    private fun setupClickListener() {
        binding.buttonOk.setOnClickListener {
            if (binding.editTextPrice.text.isNullOrEmpty()) {
                Snackbar.make(it, getString(R.string.please_input_price), Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val price = binding.editTextPrice.text.toString().toInt()
            when (priceType) {
                Product.PriceType.START -> viewModel.priceStart.value = price
                Product.PriceType.HOPE -> viewModel.priceHope.value = price
                Product.PriceType.INCREASE -> viewModel.priceIncrease.value = price
            }

            dismiss()
        }
    }

    private fun bindInfo() {
        for (info in priceInfoList) {
            addPriceInfo(info)

        }
    }

    private fun addPriceInfo(info: String) {
        val textView = TextView(context)
        textView.text = info
        binding.linearLayoutInfo.addView(textView)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}