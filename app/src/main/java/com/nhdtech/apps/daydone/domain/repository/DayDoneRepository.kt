package com.nhdtech.apps.daydone.domain.repository

import androidx.lifecycle.LiveData
import com.nhdtech.apps.daydone.data.model.Task

interface DayDoneRepository {
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    fun getAllTasks(): LiveData<List<Task>>
    suspend fun deleteAllTasks()
}