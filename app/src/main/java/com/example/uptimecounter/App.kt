package com.example.uptimecounter

import android.app.Application
import android.content.Context
import com.example.uptimecounter.data.SecondsSaver

class App : Application() {

    private val FILE_NAME = "UPTIME_SECONDS"

    override fun onCreate() {
        super.onCreate()
        SecondsSaver.setSharedPreferences(getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE))
    }
}