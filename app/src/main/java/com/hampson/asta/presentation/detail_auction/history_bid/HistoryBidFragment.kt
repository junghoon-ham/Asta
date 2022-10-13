package com.hampson.asta.presentation.detail_auction.history_bid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.FragmentHistoryBidBinding
import com.hampson.asta.domain.model.Bid
import com.hampson.asta.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryBidFragment : BaseFragment() {
    private var _binding: FragmentHistoryBidBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val dataList = arrayListOf<Bid>()
        for (i in 0..20) {
            val bid = Bid(
                userName = testName(),
                bid = testBid()
            )

            dataList.add(bid)
        }

        binding.recyclerView.apply {
            adapter = TestAdapter(dataList).apply {
                context = this@HistoryBidFragment.context
            }

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            (adapter as TestAdapter).setOnItemClickListener {
                val action =
                    HistoryBidFragmentDirections.actionFragmentHistoryBidToFragmentProfile()
                findNavController().navigate(action)
            }
        }
    }

    private val testName = arrayListOf<String>().apply {
        add("우영우")
        add("문재인")
        add("이명박")
        add("박근혜")
        add("푸틴")
        add("일론 머스크")
        add("BTS")
        add("함슨")
        add("호날두")
        add("메시")
    }

    private fun testName(): String {
        return testName.random()
    }

    private var bid = 520000
    private fun testBid(): Int {
        bid -= 20000
        return bid
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}