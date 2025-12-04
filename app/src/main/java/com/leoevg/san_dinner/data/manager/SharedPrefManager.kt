package com.leoevg.san_dinner.data.manager

import javax.inject.Inject
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

class SharedPrefManager @Inject constructor(
    // Shared Preferences - quick setup worker's Name and ID after onCreate() application
    @ApplicationContext
    private val context: Context
) {

}