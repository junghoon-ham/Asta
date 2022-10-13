package com.hampson.asta.presentation.appraisal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentAppraisalBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppraisalFragment : BaseFragment() {
    private var _binding: FragmentAppraisalBinding? = null
    private val binding get() = _binding!!

    override var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppraisalBinding.inflate(inflater, container, false)
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

        binding.recyclerView.run {
            adapter = TestAdapter(dataList).apply {
                context = this@AppraisalFragment.context
            }
            layoutManager = GridLayoutManager(context, 3)

            (adapter as TestAdapter).setOnItemClickListener {
                val action =
                    AppraisalFragmentDirections.actionFragmentAppraisalStartToFragmentDetailAppraisal()
                findNavController().navigate(action)
            }
        }
    }

    private fun testCount(): Int {
        val range = (0..500)
        return range.random()
    }

    private val testList = arrayListOf<String>().apply {
        add("함슨이 마신 커피 감정평가 부탁드려요")
        add("자체제작 마우스 감정평가좀")
        add("한쪽 잃어버린 에어팟인데")
        add("한정판인데 얼마정도인가요?")
        add("얼마에요")
        add("평가해주세요")
        add("얼마같나요?")
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