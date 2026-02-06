package com.nhdtech.apps.daydone.domain.usecase

import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.repository.DayDoneRepository

class DeleteTaskUseCase(private val dayDoneRepository: DayDoneRepository) {
    suspend fun execute(task: Task) {
        dayDoneRepository.deleteTask(task)
    }
}