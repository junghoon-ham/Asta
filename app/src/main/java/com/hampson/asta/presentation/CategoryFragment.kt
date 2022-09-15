package com.hampson.asta.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hampson.asta.databinding.FragmentCategoryBinding

class CategoryFragment : BaseFragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        //TODO: action bar menu 제거 작업
        //val menuHost: MenuHost = requireActivity()
        //menuHost.addMenuProvider(object : MenuProvider {
        //    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        //        menuInflater.inflate(R.menu.default_app_bar_menu, menu)
        //    }
//
        //    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        //        return false
        //    }
        //}, viewLifecycleOwner, Lifecycle.State.RESUMED)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}