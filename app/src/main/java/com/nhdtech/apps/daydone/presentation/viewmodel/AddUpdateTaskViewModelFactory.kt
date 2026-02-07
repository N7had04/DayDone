package com.nhdtech.apps.daydone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nhdtech.apps.daydone.domain.usecase.AddTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.UpdateTaskUseCase

class AddUpdateTaskViewModelFactory(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddUpdateTaskViewModel::class.java)) {
            return AddUpdateTaskViewModel(addTaskUseCase, updateTaskUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}