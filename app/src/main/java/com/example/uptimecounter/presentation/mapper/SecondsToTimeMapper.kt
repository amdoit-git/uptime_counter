package com.example.uptimecounter.presentation.mapper

import com.example.uptimecounter.domain.models.AppWorkTime

object SecondsToTimeMapper {

    fun map(seconds: Int): AppWorkTime {
        return AppWorkTime(seconds = seconds)
    }
}