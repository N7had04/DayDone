package com.nhdtech.apps.daydone.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nhdtech.apps.daydone.MainActivity
import com.nhdtech.apps.daydone.R
import com.nhdtech.apps.daydone.data.model.Task

object NotificationHelper {

    const val CHANNEL_ID = "task_deadline_channel"
    const val ACTION_SHOW_NOTIFICATION = "ACTION_SHOW_NOTIFICATION"
    const val ACTION_FINISH_TASK = "ACTION_FINISH_TASK"
    const val EXTRA_TASK_ID = "extra_task_id"
    const val EXTRA_TASK_TITLE = "extra_task_title"
    const val EXTRA_TASK_DESCRIPTION = "extra_task_description"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Task Deadlines",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifies when a task deadline is reached"
            }
            context.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showTaskNotification(context: Context, task: Task) {
        // Tap notification → navigate to MainFragment
        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val openAppPendingIntent = PendingIntent.getActivity(
            context,
            task.id,
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // "Finish" action button → delete task
        val finishIntent = Intent(context, TaskNotificationReceiver::class.java).apply {
            action = ACTION_FINISH_TASK
            putExtra(EXTRA_TASK_ID, task.id)
        }
        val finishPendingIntent = PendingIntent.getBroadcast(
            context,
            task.id,
            finishIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.day_done_logo)
            .setContentTitle(task.title)
            .setContentText(task.description ?: "Your task deadline has arrived!")
            .setContentIntent(openAppPendingIntent)
            .setAutoCancel(true)
            .addAction(0, "Finish", finishPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(context)
            .notify(task.id, notification)
    }
}