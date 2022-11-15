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
                    SearchFragmentDirections.actionFragmentSearchStartToFragmentDetailAuction()
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
        add("가나다라마")
        add("가습기")
        add("스미마셍")
        add("스키")
        add("ABC")
        add("물티슈")
        add("지갑")
        add("컴퓨터")
        add("가방")
        add("샤넬")
        add("아리가또")
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