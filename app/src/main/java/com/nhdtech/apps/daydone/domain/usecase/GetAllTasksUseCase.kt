package com.nhdtech.apps.daydone.domain.usecase

import com.nhdtech.apps.daydone.domain.repository.DayDoneRepository

class GetAllTasksUseCase(private val dayDoneRepository: DayDoneRepository) {
    fun execute() = dayDoneRepository.getAllTasks()
}