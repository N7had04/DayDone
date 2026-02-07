package com.nhdtech.apps.daydone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nhdtech.apps.daydone.domain.usecase.DeleteAllTasksUseCase
import com.nhdtech.apps.daydone.domain.usecase.DeleteTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.GetAllTasksUseCase

class MainViewModelFactory(
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(deleteTaskUseCase, getAllTasksUseCase, deleteAllTasksUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}