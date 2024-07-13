package com.example.uptimecounter.creator

import com.example.uptimecounter.data.repository.TimeRepositoryImpl
import com.example.uptimecounter.domain.repository.TimeRepository
import com.example.uptimecounter.domain.usecase.GetTimeUseCase
import com.example.uptimecounter.domain.usecase.SaveTimeUseCase
import com.example.uptimecounter.presentation.UpTimePresenter
import com.example.uptimecounter.presentation.UpTimeView

object Creator {

    fun provideSaveTimeUseCase(): SaveTimeUseCase {
        return SaveTimeUseCase(provideTimeRepository())
    }

    fun provideGetTimeUseCase(): GetTimeUseCase {
        return GetTimeUseCase(provideTimeRepository())
    }

    fun provideTimeRepository(): TimeRepository {
        return TimeRepositoryImpl()
    }

    fun provideUpTimePresenter(view: UpTimeView): UpTimePresenter {
        return UpTimePresenter(view)
    }
}