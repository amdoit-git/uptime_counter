package com.example.uptimecounter.presentation.mapper

import com.example.uptimecounter.domain.models.AppWorkTime

object TimeToSecondsMapper {
    fun map(time: AppWorkTime): Int {
        return time.seconds
    }
}