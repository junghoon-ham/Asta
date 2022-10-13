package com.hampson.asta.presentation.register_auction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hampson.asta.databinding.BottomSheetDialogRecyclerViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TradeBottomSheetDialog :
    BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogRecyclerViewBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<RegisterAuctionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDialogRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewTitle.text = "거래 방법 선택"

        binding.recyclerView.apply {
            adapter = TradeAdapter().apply {
                context = this@TradeBottomSheetDialog.context
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            (adapter as TradeAdapter).setOnItemClickListener {
                viewModel.trade.value = it
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}