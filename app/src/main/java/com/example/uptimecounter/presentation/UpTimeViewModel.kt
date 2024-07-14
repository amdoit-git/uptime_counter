package com.example.uptimecounter.presentation

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uptimecounter.creator.Creator
import com.example.uptimecounter.presentation.mapper.SecondsToTimeMapper
import com.example.uptimecounter.presentation.mapper.TimeToSecondsMapper
import com.example.uptimecounter.ui.SECONDS_KEY

class UpTimeViewModel(application: Application) : AndroidViewModel(application) {

    private var stopped = false

    private val saveTime = Creator.provideSaveTimeUseCase()
    private val getTime = Creator.provideGetTimeUseCase()

    val lifeData = MutableLiveData<String>()
    private val handler = Handler(Looper.getMainLooper())
    private var seconds = 0

    init {

        seconds = TimeToSecondsMapper.map(getTime.execute())

        lifeData.postValue(seconds.toString())

        doEverySecond()
    }

    fun onStart() {
        stopped = false
    }

    fun doEverySecond() {

        handler.postDelayed({
            seconds++
            doEverySecond()
        }, SECONDS_KEY, 1000L)

        lifeData.postValue(seconds.toString())

        if (stopped) {
            saveTime.execute(
                SecondsToTimeMapper.map(seconds)
            )
        }
    }

    fun onStop() {
        saveTime.execute(
            SecondsToTimeMapper.map(seconds)
        )
        stopped = true
    }

    override fun onCleared() {
        super.onCleared()

        handler.removeCallbacksAndMessages(SECONDS_KEY)
    }
}