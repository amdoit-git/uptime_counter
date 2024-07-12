package com.example.uptimecounter.data

import android.content.SharedPreferences

object SecondsSaver {
    private var sharedPreferences: SharedPreferences? = null
    private const val SECONDS_KEY = "SECONDS_KEY"

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }

    fun saveSeconds(seconds: Int) {
        sharedPreferences?.edit()?.putInt(SECONDS_KEY, seconds)?.apply()
    }

    fun getSeconds(): Int {
        return sharedPreferences?.getInt(SECONDS_KEY, 0) ?: 0
    }
}