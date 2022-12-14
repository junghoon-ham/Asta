package com.hampson.asta.presentation.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentRankingViewBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingAuctionFragment : BaseFragment() {
    private var _binding: FragmentRankingViewBinding? = null
    private val binding get() = _binding!!

    override var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRankingViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val dataList = arrayListOf<Product>()
        for (i in 0..100) {
            val product = Product(
                productName = testString(),
                currentPrice = testPrice2(),
                startPrice = testPrice1(),
                bidderCount = testCount(),
                productMainImage = testImage()
            )

            dataList.add(product)
        }


        binding.recyclerView.apply {
            adapter = TestAdapter(dataList).apply {
                context = this@RankingAuctionFragment.context
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            (adapter as TestAdapter).setOnItemClickListener {
                //val action = HomeFragmentDirections.actionFragmentHomeStartToFragmentDetailAuction()
                //findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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

    var price = 54000000
    private fun testPrice2(): Int {
        price -= 250000
        return price
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
}