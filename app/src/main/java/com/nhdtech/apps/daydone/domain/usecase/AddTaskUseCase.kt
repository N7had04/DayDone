package com.nhdtech.apps.daydone.domain.usecase

import com.nhdtech.apps.daydone.data.model.Task
import com.nhdtech.apps.daydone.domain.repository.DayDoneRepository

class AddTaskUseCase(private val dayDoneRepository: DayDoneRepository) {
    suspend fun execute(task: Task) {
        dayDoneRepository.insertTask(task)
    }
}