package com.nhdtech.apps.daydone.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.usecase.DeleteAllTasksUseCase
import com.nhdtech.apps.daydone.domain.usecase.DeleteTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.GetAllTasksUseCase
import com.nhdtech.apps.daydone.notification.NotificationScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val tasks: LiveData<List<Task>> = getAllTasksUseCase.execute()

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            NotificationScheduler.cancel(context, task)
            deleteTaskUseCase.execute(task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            tasks.value?.forEach { NotificationScheduler.cancel(context, it) }
            deleteAllTasksUseCase.execute()
        }
    }

}