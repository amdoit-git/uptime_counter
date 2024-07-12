package com.example.uptimecounter.domain.usecase

import com.example.uptimecounter.domain.models.AppWorkTime
import com.example.uptimecounter.domain.repository.TimeRepository

class GetTimeUseCase(private val repository: TimeRepository) {
    fun execute(): AppWorkTime {
        return repository.getTime()
    }
}