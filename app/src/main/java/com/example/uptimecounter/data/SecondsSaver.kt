package com.example.uptimecounter.data

import android.content.SharedPreferences
import java.util.concurrent.Executors

object SecondsSaver {
    private var sharedPreferences: SharedPreferences? = null
    private const val SECONDS_KEY = "SECONDS_KEY"
    private val executor = Executors.newCachedThreadPool()

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }

    fun saveSeconds(seconds: Int) {
        executor.execute {
            sharedPreferences?.edit()?.putInt(SECONDS_KEY, seconds)?.apply()
        }
    }

    fun getSeconds(): Int {
        return sharedPreferences?.getInt(SECONDS_KEY, 0) ?: 0
    }
}