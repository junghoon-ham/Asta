package com.hampson.asta.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.FragmentHomeBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override var bottomNavigationViewVisibility = View.VISIBLE

    private lateinit var adapter: AuctionPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val dataList = arrayListOf<Product>()
        for (i in 0..100) {
            val product = Product(
                productName = "함슨 싸인",
                currentPrice = 320000,
                startPrice = 200000,
                bidderCount = 382
            )

            dataList.add(product)
        }

        binding.recyclerView.apply {
            adapter = TestAdapter(dataList).apply { context = this@HomeFragment.context }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        }
    }

    private fun setupRecyclerView() {

        adapter = AuctionPagingAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        adapter.setOnItemClickListener {

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}