package com.nhdtech.apps.daydone.di

import com.nhdtech.apps.daydone.data.db.TaskDao
import com.nhdtech.apps.daydone.data.repository.DayDoneRepositoryImpl
import com.nhdtech.apps.daydone.domain.repository.DayDoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDayDoneRepository(taskDao: TaskDao): DayDoneRepository {
        return DayDoneRepositoryImpl(taskDao)
    }
}