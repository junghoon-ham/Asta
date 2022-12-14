package com.hampson.asta.presentation.search

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentSearchBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.domain.util.AuctionType
import com.hampson.asta.presentation.BaseFragment
import com.hampson.asta.util.Constants.SEARCH_PRODUCT_TIME_DELAY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    val dataList = mutableListOf<Product>()
    val adapter = TestAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in 0..200) {
            val product = Product(
                productName = testString(),
                currentPrice = testPrice2(),
                startPrice = testPrice1(),
                bidderCount = testCount(),
                productMainImage = testImage()
            )

            dataList.add(product)
        }

        setupSearchListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter.apply {
            context = this@SearchFragment.context
        }

        binding.recyclerView.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            (adapter as TestAdapter).setOnItemClickListener {
                val action =
                    SearchFragmentDirections.actionFragmentSearchStartToFragmentDetailAuction(
                        AuctionType.NONE
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun setupSearchListener() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.editTextSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= SEARCH_PRODUCT_TIME_DELAY) {
                text?.let { editable ->
                    val query = editable.toString().trim()
                    if (query.isNotEmpty()) {
                        val newList = dataList.filter { it.productName?.contains(query) ?: false }
                        adapter.updateDataList(newList.toMutableList())
                    } else {
                        adapter.updateDataList(mutableListOf())
                    }
                }
            }
            startTime = endTime

            binding.textViewEmptySearch.isVisible = adapter.dataList.isEmpty()
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
        add("???????????????")
        add("?????????")
        add("????????????")
        add("??????")
        add("ABC")
        add("?????????")
        add("??????")
        add("?????????")
        add("??????")
        add("??????")
        add("????????????")
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