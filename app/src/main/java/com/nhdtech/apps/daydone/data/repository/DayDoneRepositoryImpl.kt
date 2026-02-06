package com.nhdtech.apps.daydone.data.repository

import androidx.lifecycle.LiveData
import com.nhdtech.apps.daydone.data.db.TaskDao
import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.repository.DayDoneRepository

class DayDoneRepositoryImpl(private val taskDao: TaskDao) : DayDoneRepository {
    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    override suspend fun deleteAllTasks() {
       taskDao.deleteAllTasks()
    }
}