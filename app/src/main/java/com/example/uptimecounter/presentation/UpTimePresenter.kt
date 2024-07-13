package com.example.uptimecounter.presentation

import com.example.uptimecounter.creator.Creator
import com.example.uptimecounter.presentation.mapper.SecondsToTimeMapper
import com.example.uptimecounter.presentation.mapper.TimeToSecondsMapper
import com.example.uptimecounter.ui.models.UpTimeState

class UpTimePresenter(val view: UpTimeView) {

    private var stopped = false

    private val saveTime = Creator.provideSaveTimeUseCase()
    private val getTime = Creator.provideGetTimeUseCase()

    fun onCreate() {

        val seconds = TimeToSecondsMapper.map(getTime.execute())

        view.render(UpTimeState(seconds))
    }

    fun onStart() {
        stopped = false
    }

    fun doEverySecond(seconds: Int) {

        view.render(UpTimeState(seconds))

        if (stopped) {
            saveTime.execute(
                SecondsToTimeMapper.map(seconds)
            )
        }
    }

    fun onStop(seconds: Int) {
        saveTime.execute(
            SecondsToTimeMapper.map(seconds)
        )
        stopped = true
    }
}