package com.nhdtech.apps.daydone.notification

import android.Manifest
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.RequiresPermission
import com.nhdtech.apps.daydone.data.db.TaskDao
import com.nhdtech.apps.daydone.data.model.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TaskNotificationReceiver : BroadcastReceiver() {

    @Inject lateinit var taskDao: TaskDao

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("NotifReceiver", "onReceive: action=${intent.action}")

        when {
            intent.action?.startsWith(NotificationHelper.ACTION_SHOW_NOTIFICATION) == true -> {
                val task = Task(
                    id = intent.getIntExtra(NotificationHelper.EXTRA_TASK_ID, -1),
                    title = intent.getStringExtra(NotificationHelper.EXTRA_TASK_TITLE) ?: "Task Due",
                    description = intent.getStringExtra(NotificationHelper.EXTRA_TASK_DESCRIPTION),
                    deadline = 0L
                )
                Log.d("NotifReceiver", "Showing notification for task: ${task.id} ${task.title}")
                if (task.id != -1) {
                    NotificationHelper.showTaskNotification(context, task)
                }
            }

            intent.action == NotificationHelper.ACTION_FINISH_TASK -> {
                val taskId = intent.getIntExtra(NotificationHelper.EXTRA_TASK_ID, -1)
                Log.d("NotifReceiver", "Finish action for taskId: $taskId")
                if (taskId != -1) {
                    val notificationManager =
                        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.cancel(taskId)
                    CoroutineScope(Dispatchers.IO).launch {
                        taskDao.deleteById(taskId.toLong())
                    }
                }
            }
        }
    }
}