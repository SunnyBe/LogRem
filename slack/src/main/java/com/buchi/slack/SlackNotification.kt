package com.buchi.slack

import android.util.Log
import com.buchi.slack.entity.SlackBlocks
import com.buchi.slack.entity.SlackEntity
import com.buchi.slack.entity.SlackMessage
import com.buchi.slack.entity.SlackText
import com.buchi.slack.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SlackNotification {

    suspend fun sendMessageCoroutine(
        owner: String,
        message: String,
        url: String
    ): SlackEntity.SlackUploadResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val client = NetworkClient.apiService()
                val uploadResp = client.postSlackMessage(
                    url = url,
                    slackNotificationMessage = SlackText(
                        type = null,
                        owner = owner,
                        message = message
                    ).toJson()
                ).execute()

                if (uploadResp.isSuccessful) {
                    Log.d(javaClass.simpleName, uploadResp.message() ?: "")
                    SlackEntity.SlackUploadResponse(true, uploadResp.message())
                } else {
                    uploadResp.errorBody()?.string()?.let { msg ->
                        Log.d(javaClass.simpleName, msg)
                        SlackEntity.SlackUploadResponse(false, msg)
                    }
                }
            } catch (ex: Throwable) {
                ex.printStackTrace()
                Log.d(javaClass.simpleName, "System error occurred")
                SlackEntity.SlackUploadResponse(false, ex.message)
            }
        }
    }

    suspend fun sendMessageCoroutine(
        message: SlackBlocks,
        url: String
    ): SlackEntity.SlackUploadResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val client = NetworkClient.apiService()
                val uploadResp = client.postSlackMessage(
                    url = url,
                    slackNotificationMessage = message.toJson()
                ).execute()

                if (uploadResp.isSuccessful) {
                    Log.d(javaClass.simpleName, uploadResp.message() ?: "")
                    SlackEntity.SlackUploadResponse(true, uploadResp.message())
                } else {
                    uploadResp.errorBody()?.string()?.let { msg ->
                        Log.d(javaClass.simpleName, msg)
                        SlackEntity.SlackUploadResponse(false, msg)
                    }
                }
            } catch (ex: Throwable) {
                ex.printStackTrace()
                Log.d(javaClass.simpleName, "System error occurred")
                SlackEntity.SlackUploadResponse(false, ex.message)
            }
        }
    }

    fun sendMessage(
        owner: String,
        message: String,
        url: String
    ): SlackEntity.SlackUploadResponse? {
        return try {
            val client = NetworkClient.apiService()
            val uploadResp = client.postSlackMessage(
                url = url,
                slackNotificationMessage = SlackEntity.SlackMessage(
                    owner = owner,
                    text = message
                )
                    .toString()
            ).execute()

            if (uploadResp.isSuccessful) {
                Log.d(javaClass.simpleName, uploadResp.message() ?: "")
                SlackEntity.SlackUploadResponse(true, uploadResp.message())
            } else {
                uploadResp.errorBody()?.string()?.let { msg ->
                    Log.d(javaClass.simpleName, msg)
                    SlackEntity.SlackUploadResponse(false, msg)
                }
            }
        } catch (ex: Throwable) {
            ex.printStackTrace()
            Log.d(javaClass.simpleName, "System error occurred")
            SlackEntity.SlackUploadResponse(false, ex.message)
        }
    }
}