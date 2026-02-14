package com.nhdtech.apps.daydone.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhdtech.apps.daydone.R
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.databinding.FragmentMainBinding
import com.nhdtech.apps.daydone.presentation.adapter.MyRecyclerViewAdapter
import com.nhdtech.apps.daydone.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyRecyclerViewAdapter(listOf(
            Task(0, "Task 1", "Description 1", 0),
            Task(1, "Task 2", "Description 2", 1),
            Task(2, "Task 3", "Description 3", 2),
            Task(3, "Task 4", "Description 4", 3),
            Task(4, "Task 5", "Description 5", 4)
        ))
        binding.myRecyclerView.adapter = adapter

        return binding.root
    }
}