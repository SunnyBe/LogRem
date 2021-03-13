package com.buchi.logremlibrary

import android.os.Bundle
import android.widget.Toast
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
                    val response = SlackNotification.simpleMessage(
                        url = "",
                        owner = "Great Khali",
                        message = "This is the simple message I want to send to slack."
                    )
                runOnUiThread {
                    Toast.makeText(this@MainActivity,
                        "Successful: ${response?.successful}: ${response?.response}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}