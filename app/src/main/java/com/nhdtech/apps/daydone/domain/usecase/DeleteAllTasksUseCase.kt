package com.nhdtech.apps.daydone.domain.usecase

import com.nhdtech.apps.daydone.domain.repository.DayDoneRepository

class DeleteAllTasksUseCase(private val dayDoneRepository: DayDoneRepository) {
    suspend fun execute() {
        dayDoneRepository.deleteAllTasks()
    }
}