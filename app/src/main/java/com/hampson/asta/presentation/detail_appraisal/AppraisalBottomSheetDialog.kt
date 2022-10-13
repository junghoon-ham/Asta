package com.hampson.asta.presentation.detail_appraisal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hampson.asta.databinding.BottomSheetDialogAppraisalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppraisalBottomSheetDialog :
    BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogAppraisalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDialogAppraisalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}