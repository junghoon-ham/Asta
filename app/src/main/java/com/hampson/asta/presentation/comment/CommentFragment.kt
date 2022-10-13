package com.hampson.asta.presentation.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.FragmentCommentBinding
import com.hampson.asta.domain.model.Comment
import com.hampson.asta.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : BaseFragment() {
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val dataList = arrayListOf<Comment>()
        for (i in 0..50) {
            val comment = Comment(
                userName = testName(),
                comment = testComment()
            )

            dataList.add(comment)
        }

        binding.recyclerView.run {
            adapter = TestAdapter(dataList).apply {
                context = this@CommentFragment.context
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            (adapter as TestAdapter).setOnItemClickListener {
                val action = CommentFragmentDirections.actionFragmentCommentToFragmentProfile()
                findNavController().navigate(action)
            }
        }
    }

    private val testComment = arrayListOf<String>().apply {
        add("멋있네요")
        add("비싸네요")
        add("바보")
        add("한정판 인가요?")
        add("시작가가 너무 높아요")
        add("안녕하세요")
        add("ㅎㅇㅎㅇ")
        add("오늘 날씨 맑음")
        add("산책가자")
        add("감기조심")
        add("멍청이")
        add("안녕")
        add("어디야")
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

    private fun testComment(): String {
        return testComment.random()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}