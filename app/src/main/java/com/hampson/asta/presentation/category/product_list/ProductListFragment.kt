package com.hampson.asta.presentation.category.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentProductListBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.domain.util.AuctionType
import com.hampson.asta.presentation.BaseFragment
import com.hampson.asta.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : BaseFragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ProductListFragmentArgs>()

    private lateinit var adapter: TestAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarTitle(args.category.categoryName(context))
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
                context = this@ProductListFragment.context
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            (adapter as TestAdapter).setOnItemClickListener {
                val action =
                    ProductListFragmentDirections.actionFragmentProductListStartToFragmentDetailAuction(
                        AuctionType.NONE
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun setupToolbarTitle(categoryName: String?) {
        (activity as MainActivity).supportActionBar?.title = categoryName
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}