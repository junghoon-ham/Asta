package com.hampson.asta.presentation.detail_appraisal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentDetailAppraisalBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAppraisalFragment : BaseFragment() {
    private var _binding: FragmentDetailAppraisalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAppraisalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindProductImage()
        setupActionBar()
        setupRecyclerView()
        setupClickListener()
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun bindProductImage() {
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.test_img1, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img2, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img3, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img4, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img6, null, ScaleTypes.CENTER_CROP))

        binding.imageSlider.setImageList(imageList)
    }

    private fun setupRecyclerView() {
        binding.includeScreen.recyclerView.apply {
            val dataList = arrayListOf<Product>()
            for (i in 0..20) {
                val product = Product(
                    productName = testString(),
                    currentPrice = testPrice2(),
                    startPrice = testPrice1(),
                    bidderCount = testCount(),
                    productMainImage = testImage()
                )

                dataList.add(product)
            }

            adapter = TestAdapter(dataList).apply {
                context = this@DetailAppraisalFragment.context
            }


            binding.buttonAppraisal.setOnClickListener {
                //val bottomSheetBid = BidBottomSheetDialog()
                //bottomSheetBid.show(requireActivity().supportFragmentManager, "")
            }
        }
    }

    private fun setupClickListener() {
        binding.buttonAppraisal.setOnClickListener {
            val bottomSheetAppraisal = AppraisalBottomSheetDialog()
            bottomSheetAppraisal.show(requireActivity().supportFragmentManager, "")
        }

        binding.includeScreen.buttonComment.setOnClickListener {
            val action =
                DetailAppraisalFragmentDirections.actionFragmentDetailAppraisalToFragmentComment()
            findNavController().navigate(action)
        }

        val actionProfile =
            DetailAppraisalFragmentDirections.actionFragmentDetailAppraisalToFragmentProfile()

        binding.includeScreen.constraintLayoutAppraisal1.setOnClickListener {
            findNavController().navigate(actionProfile)
        }

        binding.includeScreen.constraintLayoutAppraisal2.setOnClickListener {
            findNavController().navigate(actionProfile)
        }

        binding.includeScreen.constraintLayoutBid3.setOnClickListener {
            findNavController().navigate(actionProfile)
        }

        binding.includeScreen.buttonViewMoreAppraisal.setOnClickListener {
            val action =
                DetailAppraisalFragmentDirections.actionFragmentDetailAppraisalToFragmentHistoryAppraisal()
            findNavController().navigate(action)
        }

        binding.includeScreen.constraintLayoutProfile.setOnClickListener {
            findNavController().navigate(actionProfile)
        }
    }

    private fun testCount(): Int {
        val range = (0..500)
        return range.random()
    }

    private val testList = arrayListOf<String>().apply {
        add("함슨 싸인")
        add("노트북")
        add("햄버거")
        add("충전기")
        add("휴대폰")
        add("먹다남은 피자")
        add("고장난 냉장고")
        add("BMW")
        add("전기 자전거")
        add("로봇")
        add("원두 좋은 커피")
        add("휴지 세트")
        add("다이아몬드")
    }

    private fun testString(): String {
        return testList.random()
    }

    private fun testPrice1(): Int {
        val range = (2000..30000)
        return range.random()
    }

    private fun testPrice2(): Int {
        val range = (30000..1000000)
        return range.random()
    }

    var imageList = arrayListOf<Int>().apply {
        add(R.drawable.test_img1)
        add(R.drawable.test_img2)
        add(R.drawable.test_img3)
        add(R.drawable.test_img4)
        add(R.drawable.test_img6)
        add(R.drawable.test_img7)
    }

    private fun testImage(): Int {
        return imageList.random()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}