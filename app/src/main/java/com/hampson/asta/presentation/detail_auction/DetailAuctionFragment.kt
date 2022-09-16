package com.hampson.asta.presentation.detail_auction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.hampson.asta.R
import com.hampson.asta.databinding.FragmentDetailAuctionBinding
import com.hampson.asta.domain.model.Product
import com.hampson.asta.presentation.BaseFragment
import com.hampson.asta.presentation.home.HomeFragmentDirections
import com.hampson.asta.presentation.home.TestAdapter

class DetailAuctionFragment : BaseFragment() {
    private var _binding: FragmentDetailAuctionBinding? = null
    private val binding get() = _binding!!

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

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.test_img1, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img2, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img3, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img4, null, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.test_img6, null, ScaleTypes.CENTER_CROP))

        binding.imageSlider.setImageList(imageList)

        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}