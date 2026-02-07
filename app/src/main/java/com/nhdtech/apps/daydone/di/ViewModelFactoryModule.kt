package com.nhdtech.apps.daydone.di

import com.nhdtech.apps.daydone.domain.usecase.AddTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.DeleteAllTasksUseCase
import com.nhdtech.apps.daydone.domain.usecase.DeleteTaskUseCase
import com.nhdtech.apps.daydone.domain.usecase.GetAllTasksUseCase
import com.nhdtech.apps.daydone.domain.usecase.UpdateTaskUseCase
import com.nhdtech.apps.daydone.presentation.viewmodel.AddUpdateTaskViewModelFactory
import com.nhdtech.apps.daydone.presentation.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideMainViewModelFactory(
        deleteTaskUseCase: DeleteTaskUseCase,
        getAllTasksUseCase: GetAllTasksUseCase,
        deleteAllTasksUseCase: DeleteAllTasksUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(deleteTaskUseCase, getAllTasksUseCase, deleteAllTasksUseCase)
    }

    @Singleton
    @Provides
    fun provideAddUpdateTaskViewModelFactory(
        addTaskUseCase: AddTaskUseCase,
        updateTaskUseCase: UpdateTaskUseCase
    ): AddUpdateTaskViewModelFactory {
        return AddUpdateTaskViewModelFactory(addTaskUseCase, updateTaskUseCase)
    }
}