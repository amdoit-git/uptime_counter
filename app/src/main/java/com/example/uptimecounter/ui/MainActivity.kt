package com.example.uptimecounter.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.uptimecounter.R
import com.example.uptimecounter.presentation.UpTimeViewModel

const val SECONDS_KEY = "SECONDS_KEY"

class MainActivity : AppCompatActivity() {

    private var timerText: TextView? = null

    private lateinit var viewModel: UpTimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.time)

        viewModel = ViewModelProvider(this)[UpTimeViewModel::class.java]

        viewModel.lifeData.observe(this) {
            timerText?.text = it
        }

        viewModel.live.observe(this) {
            Log.d("TEST_MODE", it.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}