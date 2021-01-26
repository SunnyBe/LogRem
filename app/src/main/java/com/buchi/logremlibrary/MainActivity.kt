package com.buchi.logremlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buchi.logremlibrary.databinding.ActivityMainBinding
import com.buchi.slack.SlackNotification
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testAction.setOnClickListener {
            GlobalScope.launch {
                SlackNotification.sendMessage(
                    url = "https://hooks.slack.com/services/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",
                    owner = "chatbot",
                    message = "Hello, Better"
                )
            }
        }
    }
}