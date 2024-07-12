package com.example.uptimecounter.data.repository

import com.example.uptimecounter.data.SecondsSaver
import com.example.uptimecounter.domain.models.AppWorkTime
import com.example.uptimecounter.domain.repository.TimeRepository

class TimeRepositoryImpl() : TimeRepository {
    override fun getTime(): AppWorkTime {
        return AppWorkTime(SecondsSaver.getSeconds())
    }

    override fun saveTime(time: AppWorkTime) {
        SecondsSaver.saveSeconds(time.seconds)
    }
}