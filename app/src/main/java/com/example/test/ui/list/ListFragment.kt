package com.example.test.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fetch_test.R
import com.example.fetch_test.databinding.FragmentListBinding
import com.example.test.common.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ListFragment : BindingFragment<FragmentListBinding>() {

    private val listViewModel by viewModels<ListViewModel>()

    override val layout: Int
        get() = R.layout.fragment_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = listViewModel

        listViewModel.showList()
    }
}