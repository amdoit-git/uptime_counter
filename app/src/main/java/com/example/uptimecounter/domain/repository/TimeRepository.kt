package com.example.uptimecounter.domain.repository

import com.example.uptimecounter.domain.models.AppWorkTime

interface TimeRepository {
    fun getTime():AppWorkTime

    fun saveTime(time:AppWorkTime)
}