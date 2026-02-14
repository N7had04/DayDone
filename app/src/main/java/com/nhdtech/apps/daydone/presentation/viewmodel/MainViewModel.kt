package com.nhdtech.apps.daydone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.usecase.DeleteAllTasksUseCase
import com.nhdtech.apps.daydone.domain.usecase.DeleteTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase
) : ViewModel() {

    val tasks: LiveData<List<Task>> = getAllTasksUseCase.execute()

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase.execute(task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            deleteAllTasksUseCase.execute()
        }
    }

}