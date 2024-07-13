package com.example.uptimecounter.presentation

import com.example.uptimecounter.ui.models.UpTimeState

interface UpTimeView {
    fun render(state: UpTimeState)
}