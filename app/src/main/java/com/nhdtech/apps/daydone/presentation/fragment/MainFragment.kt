package com.nhdtech.apps.daydone.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhdtech.apps.daydone.R
import com.nhdtech.apps.daydone.databinding.FragmentMainBinding
import com.nhdtech.apps.daydone.presentation.adapter.MyRecyclerViewAdapter
import com.nhdtech.apps.daydone.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MyRecyclerViewAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.myViewModel = mainViewModel
        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyRecyclerViewAdapter(mainViewModel.tasks.value ?: emptyList() )
        binding.myRecyclerView.adapter = adapter
        if (!mainViewModel.tasks.value.isNullOrEmpty()) {
            binding.tvEmpty.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.VISIBLE
        }
        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addUpdateFragment)
        }


        return binding.root
    }
}