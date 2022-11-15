package com.hampson.asta.presentation.register_auction

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentRegisterAuctionBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment
import com.hampson.asta.presentation.ImagePickerListener
import com.hampson.asta.presentation.MainActivity
import com.hampson.asta.presentation.register_auction.category.CategoryBottomSheetDialog
import com.hampson.asta.presentation.register_auction.condition.ConditionBottomSheetDialog
import com.hampson.asta.presentation.register_auction.deadline.DeadlineBottomSheetDialog
import com.hampson.asta.presentation.register_auction.price.PriceBottomSheetDialog
import com.hampson.asta.presentation.register_auction.trade.TradeBottomSheetDialog
import com.hampson.asta.util.collectLatestStateFlow
import com.hampson.asta.util.showSnackBar
import com.hampson.asta.util.showTwoButtonDialog
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat


@AndroidEntryPoint
class RegisterAuctionFragment : BaseFragment(), ImagePickerListener {
    private var _binding: FragmentRegisterAuctionBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val IMAGE_LIST = "IMAGE_LIST"
    }

    private val viewModel by activityViewModels<RegisterAuctionViewModel>()

    private var imageList = mutableListOf<Uri>()

    private lateinit var adapter: ImagesPickAdapter

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

        (activity as MainActivity).setImagePickerListener(this@RegisterAuctionFragment)

        bindInitData()
        setupRecyclerView()
        setupClickListener()
        setupFocusListener()
        setupObserve()
    }

    private fun bindInitData() {
        imageList = viewModel.images.value.toMutableList()
        binding.editTextTitle.setText(viewModel.title.value)
        binding.editTextInformation.setText(viewModel.information.value)
    }

    private fun setupRecyclerView() {
        adapter = ImagesPickAdapter(null).apply {
            context = this@RegisterAuctionFragment.context
        }

        adapter.replaceImages(ArrayList(viewModel.images.value))

        binding.recyclerView.apply {
            adapter = this@RegisterAuctionFragment.adapter

            (adapter as ImagesPickAdapter).setOnItemClickListener {
                (adapter as ImagesPickAdapter).removeImage(it)
                imageList.remove(it)
                viewModel.images.value = imageList
            }
        }
    }

    private fun setupObserve() {
        collectLatestStateFlow(viewModel.category) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewCategory,
                dataName = it.categoryName(context)
            )
        }

        collectLatestStateFlow(viewModel.condition) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewCondition,
                dataName = it.conditionName(context ?: requireContext())
            )
        }

        collectLatestStateFlow(viewModel.trade) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewTrade,
                dataName = it.tradeName(context ?: requireContext())
            )
        }

        collectLatestStateFlow(viewModel.priceStart) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewPriceStart,
                dataName = "시작가 ${dec.format(it)}원"
            )
        }

        collectLatestStateFlow(viewModel.priceHope) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewPriceHope,
                dataName = "희망가 ${dec.format(it)}원"
            )
        }

        collectLatestStateFlow(viewModel.priceIncrease) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewPriceIncrease,
                dataName = "호가 ${dec.format(it)}원"
            )
        }

        collectLatestStateFlow(viewModel.days) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewDeadlineDate,
                dataName = "${it}일 후"
            )
        }

        collectLatestStateFlow(viewModel.hour) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewDeadlineTime,
                dataName = "${viewModel.hour.value}시 ${viewModel.minute.value}분에 마감"
            )
        }

        collectLatestStateFlow(viewModel.minute) {
            if (it == null) return@collectLatestStateFlow

            bindUIAndWritingRegister(
                textView = binding.textViewDeadlineTime,
                dataName = "${viewModel.hour.value}시 ${viewModel.minute.value}분에 마감"
            )
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun bindUIAndWritingRegister(
        textView: TextView,
        dataName: String?
    ) {
        textView.run {
            setTextColor(Color.parseColor("#444444"))
            setTypeface(null, Typeface.BOLD)
            text = dataName
        }

        viewModel.isWritingRegister.value = true
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
            showTwoButtonDialog(
                context = context ?: requireContext(),
                message = getString(R.string.check_register_auction),
                onPositiveButton = {
                    it.showSnackBar(getString(R.string.success_register_auction))
                    (activity as MainActivity).navController.popBackStack()
                    viewModel.resetRegisterData()
                },
                onNegativeButton = {}
            )
        }

        binding.buttonOpenGallery.setOnClickListener {
            (activity as MainActivity).openImagePicker()
        }
    }

    private fun setupFocusListener() {
        binding.editTextInformation.setOnFocusChangeListener { _, focus ->
            if (!focus) {
                viewModel.information.value = binding.editTextInformation.text.toString()
            }
        }

        binding.editTextTitle.setOnFocusChangeListener { _, focus ->
            if (!focus) {
                viewModel.title.value = binding.editTextTitle.text.toString()
            }
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(IMAGE_LIST, ArrayList())
        super.onSaveInstanceState(outState)
    }

    override fun onImagePick(uri: Uri?) {
        if (uri == null) return

        imageList.clear()
        imageList.add(uri)
        adapter.replaceImages(arrayListOf(uri))

        viewModel.images.value = listOf(uri)
        viewModel.isWritingRegister.value = true
    }

    override fun onMultiImagePick(uris: List<Uri>?) {
        if (uris == null) return

        imageList.clear()
        imageList.addAll(uris.toMutableList())
        adapter.replaceImages(ArrayList(uris))

        viewModel.images.value = uris
        viewModel.isWritingRegister.value = true
    }
}