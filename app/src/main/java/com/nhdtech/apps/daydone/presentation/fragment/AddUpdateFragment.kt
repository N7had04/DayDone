package com.nhdtech.apps.daydone.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nhdtech.apps.daydone.R
import com.nhdtech.apps.daydone.databinding.FragmentAddUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUpdateFragment : Fragment() {
    private lateinit var binding: FragmentAddUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_update, container, false)
        return binding.root
    }
}