package com.buchi.slack.entity

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class SlackBlocks(
    @SerializedName("blocks") val blocks: List<SlackText>
) {
    fun toJson(): String {
        val stringJson =  Gson().toJson(this).toString()
        Log.d(javaClass.simpleName, stringJson)
        return stringJson
    }
}