package com.hampson.asta.presentation.register_auction.condition

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hampson.asta.R
import com.hampson.asta.databinding.BottomSheetDialogRecyclerViewBinding
import com.hampson.asta.presentation.explanation_bottom_sheet.condition.ExplanationConditionAdapter
import com.hampson.asta.presentation.register_auction.RegisterAuctionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConditionBottomSheetDialog :
    BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogRecyclerViewBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<RegisterAuctionViewModel>()

    private var behaviour: BottomSheetBehavior<View>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(context ?: requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                behaviour = BottomSheetBehavior.from(it)

                val layoutParams = it.layoutParams
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                it.layoutParams = layoutParams

                behaviour?.peekHeight = it.height
            }
        }
        return dialog
    }

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

        bindTitle()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = ExplanationConditionAdapter().apply {
                context = this@ConditionBottomSheetDialog.context
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            (adapter as ExplanationConditionAdapter).setOnItemClickListener {
                viewModel.condition.value = it
                dismiss()
            }
        }
    }

    private fun bindTitle() {
        binding.textViewTitle.text = getString(R.string.select_condition)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}