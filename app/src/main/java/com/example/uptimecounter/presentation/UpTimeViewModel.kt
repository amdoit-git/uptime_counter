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

class LiveData<T> : MutableLiveData<T>()

class UpTimeViewModel(application: Application) : AndroidViewModel(application) {

    private var stopped = false

    private val saveTime = Creator.provideSaveTimeUseCase()
    private val getTime = Creator.provideGetTimeUseCase()

    val lifeData = LiveData<String>()
    private val handler = Handler(Looper.getMainLooper())
    private var seconds = 0
    private var index = 1

    val live = LiveDataWithStartDataSet<Test>()

    init {

        seconds = TimeToSecondsMapper.map(getTime.execute())

        lifeData.postValue(seconds.toString())

        doEverySecond()

        testTimer()
    }

    fun getData(): LiveData<String> {
        return lifeData
    }

    fun onStart() {
        stopped = false
    }

    fun testTimer(){

        handler.postDelayed({
            index++
            if(index<7) testTimer()
        }, SECONDS_KEY, 5000L)

        if(index%2==0){
            live.postValue(Test.T1(index))
        }
        else{

            val list = mutableListOf<Int>()

            for (i in 0 until index){
                list.add(i)
            }

            live.postValue(Test.T2(list))
        }
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