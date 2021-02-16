package com.buchi.logremlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buchi.logremlibrary.databinding.ActivityMainBinding
import com.buchi.slack.SlackNotification
import com.buchi.slack.entity.SlackBlocks
import com.buchi.slack.entity.SlackText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val headerText = SlackText.headerText("Test Header")
        val sectionText = SlackText.sectionText(*arrayOf("First Section", "Second Section"))
        val actions = SlackText.actionsText(mapOf("primary" to "Approve"), mapOf("danger" to "Decline"))
        binding.testAction.setOnClickListener {
            GlobalScope.launch {
                SlackNotification.sendMessageCoroutine(
                    url = "",
                    message = SlackBlocks(listOf(headerText, sectionText, actions))
                )
            }
        }
    }
}