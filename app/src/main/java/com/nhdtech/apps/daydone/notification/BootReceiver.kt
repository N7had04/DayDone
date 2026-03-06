package com.nhdtech.apps.daydone.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.nhdtech.apps.daydone.data.db.TaskDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject lateinit var taskDao: TaskDao

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                taskDao.getAllTasksList()
                    .filter { it.deadline > System.currentTimeMillis() }
                    .forEach { NotificationScheduler.schedule(context, it) }
            }
        }
    }
}