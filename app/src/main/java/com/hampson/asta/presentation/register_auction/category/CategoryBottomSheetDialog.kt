package com.hampson.asta.presentation.register_auction.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hampson.asta.databinding.BottomSheetDialogRegisterCategoryBinding
import com.hampson.asta.presentation.category.CategoryAdapter
import com.hampson.asta.presentation.register_auction.RegisterAuctionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryBottomSheetDialog :
    BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogRegisterCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<RegisterAuctionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDialogRegisterCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = CategoryAdapter().apply {
                context = this@CategoryBottomSheetDialog.context
            }

            (adapter as CategoryAdapter).setOnItemClickListener {
                viewModel.category.value = it
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}