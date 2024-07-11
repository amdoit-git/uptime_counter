package com.example.uptimecounter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val FILE_NAME = "UPTIME_SECONDS"
const val SECONDS_KEY = "SECONDS_KEY"

class MainActivity : AppCompatActivity() {

    private var timerText: TextView? = null
    private var sharedPreferences: SharedPreferences? = null
    private val handler = Handler(Looper.getMainLooper())
    private var seconds: Int = 0
    private var stopped = false

    init {
        doEverySecond()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        sharedPreferences = applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        timerText = findViewById(R.id.time)

        savedInstanceState?.let {
            seconds = it.getInt(SECONDS_KEY, 0)
        } ?: run {
            seconds = sharedPreferences?.getInt(SECONDS_KEY, 0) ?: 0
        }

        timerText?.text = seconds.toString()
    }

    override fun onStart() {
        super.onStart()
        stopped = false
    }

    private fun doEverySecond() {

        handler.postDelayed({
            seconds++
            doEverySecond()
        }, SECONDS_KEY, 1000L)

        timerText?.text = seconds.toString()

        if (stopped) {
            sharedPreferences?.edit()?.putInt(SECONDS_KEY, seconds)?.apply()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SECONDS_KEY, seconds)
    }

    override fun onStop() {
        super.onStop()
        sharedPreferences?.edit()?.putInt(SECONDS_KEY, seconds)?.apply()
        stopped = true
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {
            handler.removeCallbacksAndMessages(SECONDS_KEY)
        }
    }
}