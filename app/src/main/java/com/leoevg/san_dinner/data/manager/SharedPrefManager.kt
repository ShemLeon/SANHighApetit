package com.leoevg.san_dinner.data.manager

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(
    // Shared Preferences - quick setup worker's Name and ID after onCreate() application
    @ApplicationContext
    private val context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("san_dinner_prefs", Context.MODE_PRIVATE)

    var firstName: String
        get() = sharedPreferences.getString("first_name", "") ?: ""
        set(value) = sharedPreferences.edit().putString("first_name", value).apply()

    var workerID: String
        get() = sharedPreferences.getString("worker_id", "") ?: ""
        set(value) = sharedPreferences.edit().putString("worker_id", value).apply()

    var language: String
        get() = sharedPreferences.getString("language", "RU") ?: "RU"
        set(value) = sharedPreferences.edit().putString("language", value).apply()

    var notificationsEnabled: Boolean
        get() = sharedPreferences.getBoolean("notifications_enabled", true)
        set(value) = sharedPreferences.edit().putBoolean("notifications_enabled", value).apply()
}
