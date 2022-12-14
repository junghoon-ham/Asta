package com.hampson.asta.presentation.detail_auction

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentDetailAuctionBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.domain.util.AuctionType
import com.hampson.asta.presentation.BaseFragment
import com.hampson.asta.presentation.explanation_bottom_sheet.condition.ExplanationConditionBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DetailAuctionFragment : BaseFragment() {
    private var _binding: FragmentDetailAuctionBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailAuctionFragmentArgs>()
    private var auctionType: AuctionType? = null

    private var maxCount: Long = 3 * 100000
    private val interval: Long = 1000
    private var count = 300

    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAuctionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auctionType = args.auctionType

        bindStatusUI()
        bindProductImage()
        setupActionButton()
        setupActionBar()
        setupRecyclerView()
        setupClickListener()

        countDownTimer()
        countDownTimer?.start()
    }

    private fun bindProductImage() {
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.test_img6, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img4, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img3, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img2, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img1, null, ScaleTypes.CENTER_CROP))

        binding.imageSlider.setImageList(imageList)
    }

    private fun bindStatusUI() {
        with(binding.includeStatus) {
            root.isVisible = true
            constraintLayoutRoot.setBackgroundResource(auctionType?.typeDrawable() ?: 0)
            textViewStatus.run {
                text = auctionType?.typeName(context)
                setTextColor(resources.getColor(auctionType?.typeColor() ?: 0))
            }
        }
    }

    private fun setupActionButton() {
        binding.buttonBid.text = auctionType?.actionButtonMessage(context)

        when (auctionType) {
            AuctionType.BIDDING,
            AuctionType.NONE -> {
                binding.buttonBid.run {
                    isEnabled = true
                    binding.buttonBid.setBackgroundResource(R.drawable.round_6_background_ff5a5a)
                }
            }
        }
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun setupRecyclerView() {
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

        binding.includeScreen.recyclerView.apply {
            adapter = TestAdapter(dataList).apply {
                context = this@DetailAuctionFragment.context
            }

            //(adapter as TestAdapter).setOnItemClickListener {
            //    val action = HomeFragmentDirections.actionFragmentHomeStartToFragmentDetailAuction()
            //    findNavController().navigate(action)
            //}
        }
    }

    private fun setupClickListener() {
        binding.buttonBid.setOnClickListener {
            val bottomSheetBid = BidBottomSheetDialog()
            bottomSheetBid.show(requireActivity().supportFragmentManager, "")
        }

        binding.includeScreen.buttonComment.setOnClickListener {
            val action =
                DetailAuctionFragmentDirections.actionFragmentDetailAuctionToFragmentComment()
            findNavController().navigate(action)
        }

        val actionProfile =
            DetailAuctionFragmentDirections.actionFragmentDetailAuctionToFragmentProfile()

        binding.includeScreen.constraintLayoutBid1.setOnClickListener {
            findNavController().navigate(actionProfile)
        }

        binding.includeScreen.constraintLayoutBid2.setOnClickListener {
            findNavController().navigate(actionProfile)
        }

        binding.includeScreen.constraintLayoutBid3.setOnClickListener {
            findNavController().navigate(actionProfile)
        }

        binding.includeScreen.buttonViewMoreBid.setOnClickListener {
            val action =
                DetailAuctionFragmentDirections.actionFragmentDetailAuctionToFragmentHistoryBid()
            findNavController().navigate(action)
        }

        binding.includeScreen.constraintLayoutProfile.setOnClickListener {
            findNavController().navigate(actionProfile)
        }

        binding.includeScreen.buttonCondition.setOnClickListener {
            val bottomSheetDialog = ExplanationConditionBottomSheetDialog()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "")
        }
    }

    private fun testCount(): Int {
        val range = (0..500)
        return range.random()
    }

    private val testList = arrayListOf<String>().apply {
        add("?????? ??????")
        add("?????????")
        add("?????????")
        add("?????????")
        add("?????????")
        add("???????????? ??????")
        add("????????? ?????????")
        add("BMW")
        add("?????? ?????????")
        add("??????")
        add("?????? ?????? ??????")
        add("?????? ??????")
        add("???????????????")
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

    private fun countDownTimer() {
        countDownTimer = object : CountDownTimer(maxCount, interval) {
            override fun onTick(p0: Long) {
                binding.includeScreen.textViewDeadline.text = formatTime(maxCount)
                maxCount -= 1
            }

            override fun onFinish() {

            }
        }
    }

    private fun formatTime(seconds: Long): String {
        val day = TimeUnit.SECONDS.toDays(seconds).toInt()
        val hours = TimeUnit.SECONDS.toHours(seconds) - day * 24
        val minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
        val second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60
        println(seconds.toString() + " ==> " + day.toString() + "??? " + hours + "??? " + minute + "??? " + second + "???")
        return "${day}??? ${hours}??? ${minute}??? ${second}???"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            countDownTimer?.cancel()
        } catch (e: Exception) {
        }

        countDownTimer = null
    }

    override fun onStop() {
        super.onStop()

        try {
            countDownTimer?.cancel()
        } catch (e: Exception) {
        }

        countDownTimer = null
    }
}