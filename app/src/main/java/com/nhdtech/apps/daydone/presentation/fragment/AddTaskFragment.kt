package com.nhdtech.apps.daydone.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nhdtech.apps.daydone.R
import com.nhdtech.apps.daydone.databinding.FragmentAddUpdateBinding
import com.nhdtech.apps.daydone.presentation.viewmodel.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddUpdateBinding
    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_update, container, false)
        binding.lifecycleOwner = this
        binding.myViewModel = viewModel
        return binding.root
    }
}