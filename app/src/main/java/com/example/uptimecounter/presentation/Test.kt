package com.example.uptimecounter.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


sealed interface Test {
    data class T1(var i: Int) : Test
    data class T2(var list: List<Int>) : Test
}