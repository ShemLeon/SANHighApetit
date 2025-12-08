package com.leoevg.san_dinner.presentation.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.leoevg.san_dinner.MainActivity
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.data.manager.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    override fun onReceive(context: Context, intent: Intent) {
        // Если уведомления отключены пользователем, ничего не делаем
        if (!sharedPrefManager.notificationsEnabled) return

        val today = LocalDate.now()
        val dayOfWeek = today.dayOfWeek

        // Проверка дней: Воскресенье - Четверг
        val isWorkingDay = dayOfWeek == DayOfWeek.SUNDAY ||
                dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY

        if (isWorkingDay) {
            showNotification(context)
        }
    }

    private fun showNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "san_dinner_reminder"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Напоминание о заказе",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Напоминает заказать еду до 9:30"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Не забудьте заказать еду!")
            .setContentText("Время 9:30, а вы еще не сделали заказ на сегодня.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1001, notification)
    }
}
