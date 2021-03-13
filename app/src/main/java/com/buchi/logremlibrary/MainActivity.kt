package com.buchi.logremlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buchi.logremlibrary.databinding.ActivityMainBinding
import com.buchi.slack.SlackNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testAction.setOnClickListener {
            GlobalScope.launch {
                withContext(Dispatchers.Default) {
                    SlackNotification.simpleMessage(
                        url = "",
                        owner = "Great Khali",
                        message = "This is the simple message I want to send"
                    )
                }
            }
        }
    }
}