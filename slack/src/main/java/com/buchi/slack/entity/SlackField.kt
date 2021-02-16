package com.buchi.slack.entity

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class SlackField(
    @SerializedName("fields") val fields: List<SlackText>
) {
    fun toJson(): String {
        val stringJson =  Gson().toJson(this).toString()
        Log.d(javaClass.simpleName, stringJson)
        return stringJson
    }
}

/*
{
	"blocks": [
		{
			"type": "header",
			"text": {
				"type": "plain_text",
				"text": "New request",
				"emoji": true
			}
		},
		{
			"type": "section",
			"fields": [
				{
					"type": "mrkdwn",
					"text": "*Type:*\nPaid Time Off"
				},
				{
					"type": "mrkdwn",
					"text": "*Created by:*\n<example.com|Fred Enriquez>"
				}
			]
		},
		{
			"type": "section",
			"fields": [
				{
					"type": "mrkdwn",
					"text": "*When:*\nAug 10 - Aug 13"
				}
			]
		},
		{
			"type": "actions",
			"elements": [
				{
					"type": "button",
					"text": {
						"type": "plain_text",
						"emoji": true,
						"text": "Approve"
					},
					"style": "primary",
					"value": "click_me_123"
				},
				{
					"type": "button",
					"text": {
						"type": "plain_text",
						"emoji": true,
						"text": "Reject"
					},
					"style": "danger",
					"value": "click_me_123"
				}
			]
		}
	]
}
 */