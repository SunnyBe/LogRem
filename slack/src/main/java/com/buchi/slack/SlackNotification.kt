package com.buchi.slack

import android.util.Log
import com.buchi.slack.entity.SlackBlocks
import com.buchi.slack.entity.SlackEntity
import com.buchi.slack.entity.SlackText
import com.buchi.slack.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SlackNotification {

    fun sendMessage(
        message: SlackBlocks,
        url: String
    ): SlackEntity.SlackUploadResponse? {
        val client = NetworkClient.apiService()
        val uploadResp = client.postSlackMessage(
            url = url,
            slackNotificationMessage = message.toJson()
        ).execute()

        return if (uploadResp.isSuccessful) {
            Log.d(javaClass.simpleName, uploadResp.message() ?: "")
            SlackEntity.SlackUploadResponse(true, uploadResp.message())
        } else {
            uploadResp.errorBody()?.string()?.let { msg ->
                Log.d(javaClass.simpleName, msg)
                SlackEntity.SlackUploadResponse(false, msg)
            }
        }
    }

    /**
     * Upload a very simple message to the specified slack web-hook url. All parameters are expected
     * to be provided
     * @param url of the web-hook app to send message to. Assumption is that consumer already have a
     * web hook.
     * @param owner of the message being sent. By default mobile-app is used
     * @param message that would be sent to the configured channel while creating slack web-hook.
     */
    fun simpleMessage(url: String, owner: String = "mobile-app", message: String) {
        val client = NetworkClient.apiService()
        val uploadResp = client.postSlackMessage(
            url = url,
            slackNotificationMessage = SlackText.simpleText(owner, message)
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
    }
}