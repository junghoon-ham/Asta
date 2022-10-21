package com.hampson.asta.presentation.register_auction

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentRegisterAuctionBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment
import com.hampson.asta.presentation.MainActivity
import com.hampson.asta.presentation.register_auction.category.CategoryBottomSheetDialog
import com.hampson.asta.presentation.register_auction.condition.ConditionBottomSheetDialog
import com.hampson.asta.presentation.register_auction.deadline.DeadlineBottomSheetDialog
import com.hampson.asta.presentation.register_auction.price.PriceBottomSheetDialog
import com.hampson.asta.presentation.register_auction.trade.TradeBottomSheetDialog
import com.hampson.asta.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat


@AndroidEntryPoint
class RegisterAuctionFragment : BaseFragment() {
    private var _binding: FragmentRegisterAuctionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<RegisterAuctionViewModel>()

    val dec = DecimalFormat("#,###")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterAuctionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListener()
        setupObserve()
    }

    private fun setupObserve() {
        collectLatestStateFlow(viewModel.category) {
            if (it == null) return@collectLatestStateFlow
            bindUI(binding.textViewCategory, it.categoryName(context))
        }

        collectLatestStateFlow(viewModel.condition) {
            if (it == null) return@collectLatestStateFlow
            bindUI(binding.textViewCondition, it.conditionName(context ?: requireContext()))
        }

        collectLatestStateFlow(viewModel.trade) {
            if (it == null) return@collectLatestStateFlow
            bindUI(binding.textViewTrade, it.tradeName(context ?: requireContext()))
        }

        collectLatestStateFlow(viewModel.priceStart) {
            if (it == null) return@collectLatestStateFlow
            bindUI(binding.textViewPriceStart, "시작가 ${dec.format(it)}원")
        }

        collectLatestStateFlow(viewModel.priceHope) {
            if (it == null) return@collectLatestStateFlow
            bindUI(binding.textViewPriceHope, "희망가 ${dec.format(it)}원")
        }

        collectLatestStateFlow(viewModel.priceIncrease) {
            if (it == null) return@collectLatestStateFlow
            bindUI(binding.textViewPriceIncrease, "호가 ${dec.format(it)}원")
        }

        collectLatestStateFlow(viewModel.days) {
            if (it == null) return@collectLatestStateFlow
            bindUI(binding.textViewDeadlineDate, "${it}일 후")
        }

        collectLatestStateFlow(viewModel.hour) {
            if (it == null) return@collectLatestStateFlow
            bindUI(
                binding.textViewDeadlineTime,
                "${viewModel.hour.value}시 ${viewModel.minute.value}분에 마감"
            )
        }

        collectLatestStateFlow(viewModel.minute) {
            if (it == null) return@collectLatestStateFlow
            bindUI(
                binding.textViewDeadlineTime,
                "${viewModel.hour.value}시 ${viewModel.minute.value}분에 마감"
            )
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun bindUI(
        textView: TextView,
        dataName: String?
    ) {
        textView.run {
            setTextColor(Color.parseColor("#444444"))
            setTypeface(null, Typeface.BOLD)
            text = dataName
        }
    }

    private fun setupClickListener() {
        binding.constraintLayoutCategory.setOnClickListener {
            val bottomSheetDialog = CategoryBottomSheetDialog()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }

        binding.constraintLayoutCondition.setOnClickListener {
            val bottomSheetDialog = ConditionBottomSheetDialog()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }

        binding.constraintLayoutTrade.setOnClickListener {
            val bottomSheetDialog = TradeBottomSheetDialog()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }

        binding.constraintLayoutPriceHope.setOnClickListener {
            val bottomSheetDialog = PriceBottomSheetDialog(
                priceType = Product.PriceType.HOPE
            )
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }

        binding.constraintLayoutPriceStart.setOnClickListener {
            val bottomSheetDialog = PriceBottomSheetDialog(
                priceType = Product.PriceType.START
            )
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }

        binding.constraintLayoutPriceIncrease.setOnClickListener {
            val bottomSheetDialog = PriceBottomSheetDialog(
                priceType = Product.PriceType.INCREASE
            )
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }

        binding.constraintLayoutDeadlineDate.setOnClickListener {
            val bottomSheetDialog = DeadlineBottomSheetDialog()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }

        binding.constraintLayoutDeadlineTime.setOnClickListener {
            setupTimePicker()

        }

        binding.buttonRegisterAuction.setOnClickListener {

            MaterialAlertDialogBuilder(
                requireContext(),
                R.style.ThemeOverlay_App_MaterialAlertDialog
            ).setMessage("경매를 등록하시겠습니까?")
                .setTitle("안내")
                .setNegativeButton("취소") { dialog, which ->

                }
                .setPositiveButton("확인") { dialog, which ->
                    Snackbar.make(it, "경매등록이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
                    (activity as MainActivity).navController.popBackStack()
                }
                .show()
        }

        binding.buttonOpenGallery.setOnClickListener {
            openGalley()
        }
    }

    private fun setupTimePicker() {
        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setPositiveButtonText("확인")
                .setNegativeButtonText("취소")
                .setTitleText("마감 시간")
                .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                .build()

        timePicker.show(requireActivity().supportFragmentManager, "")
        timePicker.addOnPositiveButtonClickListener {
            viewModel.hour.value = timePicker.hour
            viewModel.minute.value = timePicker.minute
        }
    }

    private fun openGalley() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selcet Picture"), 123)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}