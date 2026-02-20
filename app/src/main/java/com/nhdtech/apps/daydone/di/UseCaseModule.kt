package com.nhdtech.apps.daydone.di

import com.nhdtech.apps.daydone.domain.repository.DayDoneRepository
import com.nhdtech.apps.daydone.domain.usecase.AddTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.DeleteAllTasksUseCase
import com.nhdtech.apps.daydone.domain.usecase.DeleteTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.GetAllTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideAddTaskUseCase(dayDoneRepository: DayDoneRepository): AddTaskUseCase {
        return AddTaskUseCase(dayDoneRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteTaskUseCase(dayDoneRepository: DayDoneRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(dayDoneRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllTasksUseCase(dayDoneRepository: DayDoneRepository): GetAllTasksUseCase {
        return GetAllTasksUseCase(dayDoneRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteAllTasksUseCase(dayDoneRepository: DayDoneRepository): DeleteAllTasksUseCase {
        return DeleteAllTasksUseCase(dayDoneRepository)
    }
}