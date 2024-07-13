package com.example.uptimecounter.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uptimecounter.R
import com.example.uptimecounter.creator.Creator
import com.example.uptimecounter.presentation.UpTimeView
import com.example.uptimecounter.presentation.mapper.SecondsToTimeMapper
import com.example.uptimecounter.presentation.mapper.TimeToSecondsMapper
import com.example.uptimecounter.ui.models.UpTimeState

const val SECONDS_KEY = "SECONDS_KEY"

class MainActivity : AppCompatActivity(), UpTimeView {

    private var timerText: TextView? = null
    private val handler = Handler(Looper.getMainLooper())
    private var seconds: Int = 0

    private val presenter = Creator.provideUpTimePresenter(this)

    init {
        doEverySecond()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.time)

        savedInstanceState?.let {
            seconds = it.getInt(SECONDS_KEY, 0)
            timerText?.text = seconds.toString()
        } ?: run {
            presenter.onCreate()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    private fun doEverySecond() {

        handler.postDelayed({
            seconds++
            doEverySecond()
        }, SECONDS_KEY, 1000L)

        presenter.doEverySecond(seconds)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SECONDS_KEY, seconds)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop(seconds)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {
            handler.removeCallbacksAndMessages(SECONDS_KEY)
        }
    }

    override fun render(state: UpTimeState) {
        timerText?.text = state.seconds.toString()
        seconds = state.seconds
    }
}