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
import com.app.imagepickerlibrary.ImagePicker
import com.app.imagepickerlibrary.ImagePicker.Companion.registerImagePicker
import com.app.imagepickerlibrary.listener.ImagePickerResultListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentRegisterAuctionBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment
import com.hampson.asta.presentation.MainActivity
import com.hampson.asta.util.PickerOptions
import com.hampson.asta.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat


@AndroidEntryPoint
class RegisterAuctionFragment : BaseFragment(), ImagePickerResultListener {
    private var _binding: FragmentRegisterAuctionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<RegisterAuctionViewModel>()

    private lateinit var imagePicker: ImagePicker

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

        imagePicker = registerImagePicker(this)

        setupRecyclerView()
        setupClickListener()
        setupObserve()
    }

    private fun setupRecyclerView() {
        adapter = ImagesPickAdapter(null).apply {
            context = this@RegisterAuctionFragment.context
        }

        binding.recyclerView.apply {
            adapter = this@RegisterAuctionFragment.adapter

            (adapter as ImagesPickAdapter).setOnItemClickListener {
                (adapter as ImagesPickAdapter).removeImage(it)
            }
        }
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
            openImagePicker()
        }
    }


    private fun openImagePicker() {
        val pickerOptions = PickerOptions.default()

        imagePicker
            .title(getString(R.string.select_auction_product))
            .multipleSelection(pickerOptions.allowMultipleSelection, pickerOptions.maxPickCount)
            .showCountInToolBar(pickerOptions.showCountInToolBar)
            .showFolder(pickerOptions.showFolders)
            .cameraIcon(pickerOptions.showCameraIconInGallery)
            .doneIcon(pickerOptions.isDoneIcon)
            .allowCropping(pickerOptions.openCropOptions)
            .compressImage(pickerOptions.compressImage)
            .maxImageSize(pickerOptions.maxPickSizeMB)
            .extension(pickerOptions.pickExtension)

        imagePicker.open(pickerOptions.pickerType)
    }

    override fun onImagePick(uri: Uri?) {
        if (uri == null) return
        adapter.replaceImages(arrayListOf(uri))
    }

    override fun onMultiImagePick(uris: List<Uri>?) {
        adapter.replaceImages(ArrayList(uris ?: arrayListOf()))
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}