package com.nhdtech.apps.daydone.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.nhdtech.apps.daydone.data.model.Task

object NotificationScheduler {

    fun schedule(context: Context, task: Task) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val canSchedule = alarmManager.canScheduleExactAlarms()
            Log.d("NotifScheduler", "canScheduleExactAlarms: $canSchedule")
            if (!canSchedule) {
                // Open settings so user can grant permission
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
                return
            }
        }

        Log.d("NotifScheduler", "Scheduling task: id=${task.id} title=${task.title}")
        Log.d("NotifScheduler", "Fires in ${(task.deadline - System.currentTimeMillis()) / 1000}s")

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            task.deadline,
            buildPendingIntent(context, task)
        )
    }

    fun cancel(context: Context, task: Task) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(buildPendingIntent(context, task))
    }

    private fun buildPendingIntent(context: Context, task: Task): PendingIntent {
        val intent = Intent(context, TaskNotificationReceiver::class.java).apply {
            action = "${NotificationHelper.ACTION_SHOW_NOTIFICATION}_${task.id}"
            putExtra(NotificationHelper.EXTRA_TASK_ID, task.id)
            putExtra(NotificationHelper.EXTRA_TASK_TITLE, task.title)
            putExtra(NotificationHelper.EXTRA_TASK_DESCRIPTION, task.description)
        }
        return PendingIntent.getBroadcast(
            context,
            task.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}