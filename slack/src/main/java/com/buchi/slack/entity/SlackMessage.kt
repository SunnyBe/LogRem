package com.buchi.slack.entity

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

sealed class SlackMessage {
    data class Message(
        val message: String
    ): SlackMessage()

    data class MessageText(
        val message: SlackText
    ): SlackMessage()

    fun toJson(): String {
        val stringJson =  Gson().toJson(this).toString()
        Log.d(javaClass.simpleName, stringJson)
        return stringJson
    }
}