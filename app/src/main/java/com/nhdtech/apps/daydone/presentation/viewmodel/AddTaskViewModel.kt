package com.nhdtech.apps.daydone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.usecase.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
): ViewModel() {

    fun addTask(task: Task) {
        viewModelScope.launch {
            addTaskUseCase.execute(task)
        }
    }
}