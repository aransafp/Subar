package com.aransafp.subar.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.aransafp.subar.MainActivity
import com.aransafp.subar.R
import java.util.*

class DailyReminder : BroadcastReceiver() {

    companion object {
        const val ID_REPEATING_ALARM = 101
        const val ID_NOTIFICATION = 1
        const val CHANNEL_ID = "channel id"
        const val CHANNEL_NAME = "channel name"
        const val CHANNEL_DESC = "channel desc"
    }

    override fun onReceive(context: Context, intent: Intent) {

        showNotification(context)
    }

    fun setAlarm(context: Context) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, DailyReminder::class.java).let { intent ->
            PendingIntent.getBroadcast(context, ID_REPEATING_ALARM, intent, 0)
        }

        //set the alarm to start at 7.00 a.m.
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 7)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )

    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, DailyReminder::class.java).let { intent ->
            PendingIntent.getBroadcast(context, ID_REPEATING_ALARM, intent, 0)
        }

        alarmManager.cancel(alarmIntent)
    }

    private fun showNotification(context: Context) {

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //explicit intent to open home activity
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(context.getString(R.string.title_notification))
            .setContentText(context.getString(R.string.msg_notification))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESC
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(ID_NOTIFICATION, builder.build())

    }

}