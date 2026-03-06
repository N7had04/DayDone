package com.nhdtech.apps.daydone.presentation.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nhdtech.apps.daydone.R
import com.nhdtech.apps.daydone.databinding.FragmentAddUpdateBinding
import com.nhdtech.apps.daydone.presentation.viewmodel.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

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

        viewModel.taskSaved.observe(viewLifecycleOwner) { saved ->
            if (saved) {
                findNavController().popBackStack()
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            etDeadline.setOnClickListener {
                openDatePicker()
            }
            fabSaveTask.setOnClickListener {
                if (etTitle.text.toString().isNotEmpty() && etDeadline.text.toString().isNotEmpty()) {
                    viewModel.addTask(
                        title = etTitle.text.toString(),
                        description = etDescription.text.toString()
                    )
                } else {
                    Toast.makeText(requireContext(), "Please enter task and specify deadline!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->

                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                openTimePicker(calendar)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun openTimePicker(calendar: Calendar) {

        val timePicker = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->

                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                calendar.set(Calendar.SECOND, 0)

                showSelectedDate(calendar)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )

        timePicker.show()
    }

    private fun showSelectedDate(calendar: Calendar) {
        viewModel.setSelectedDeadline(calendar)
    }
}