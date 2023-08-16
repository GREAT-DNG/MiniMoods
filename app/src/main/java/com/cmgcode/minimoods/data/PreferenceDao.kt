package com.cmgcode.minimoods.data

import android.content.Context
import androidx.preference.PreferenceManager
import com.cmgcode.minimoods.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PreferenceDao @Inject constructor(@ApplicationContext context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val loggingKey = context.getString(R.string.key_should_log_crashes)

    var shouldReportCrashes: Boolean?
        get() {
            if (!preferences.contains(loggingKey)) {
                return null
            }

            return preferences.getBoolean(loggingKey, false)
        }
        set(value) {
            preferences
                .edit()
                .run {
                    if (value == null) {
                        remove(loggingKey)
                    } else {
                        putBoolean(loggingKey, value)
                    }
                }
                .apply()
        }
}
