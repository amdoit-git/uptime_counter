package com.example.uptimecounter.domain.usecase

import com.example.uptimecounter.domain.models.AppWorkTime
import com.example.uptimecounter.domain.repository.TimeRepository

class SaveTimeUseCase(private val repository: TimeRepository) {
    fun execute(time:AppWorkTime){
        repository.saveTime(time)
    }
}