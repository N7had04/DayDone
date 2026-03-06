package com.nhdtech.apps.daydone

import android.app.Application
import com.nhdtech.apps.daydone.notification.NotificationHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DayDoneApp: Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }
}