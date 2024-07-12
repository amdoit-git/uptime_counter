package com.example.uptimecounter.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uptimecounter.R
import com.example.uptimecounter.creator.Creator
import com.example.uptimecounter.presentation.mapper.SecondsToTimeMapper
import com.example.uptimecounter.presentation.mapper.TimeToSecondsMapper

const val SECONDS_KEY = "SECONDS_KEY"

class MainActivity : AppCompatActivity() {

    private var timerText: TextView? = null
    private val handler = Handler(Looper.getMainLooper())
    private var seconds: Int = 0
    private var stopped = false

    private val saveTime = Creator.provideSaveTimeUseCase()
    private val getTime = Creator.provideGetTimeUseCase()

    init {
        doEverySecond()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.time)

        savedInstanceState?.let {
            seconds = it.getInt(SECONDS_KEY, 0)
        } ?: run {
            seconds = TimeToSecondsMapper.map(getTime.execute())
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
            saveTime.execute(
                SecondsToTimeMapper.map(seconds)
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SECONDS_KEY, seconds)
    }

    override fun onStop() {
        super.onStop()
        saveTime.execute(
            SecondsToTimeMapper.map(seconds)
        )
        stopped = true
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {
            handler.removeCallbacksAndMessages(SECONDS_KEY)
        }
    }
}