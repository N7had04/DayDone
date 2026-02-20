package com.nhdtech.apps.daydone.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nhdtech.apps.daydone.data.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}