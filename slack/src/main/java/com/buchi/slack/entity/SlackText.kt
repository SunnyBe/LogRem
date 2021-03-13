package com.buchi.slack.entity

import android.util.Log
import com.buchi.slack.util.SuperclassExclusionStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * A must include object for entering the message itself. This can be sent on it's own or as a
 * parameter in another object or as an object in [blocks] array. Find more info on https://api.slack.com/messaging/interactivity
 * @param type of the Slack text being sent. Options are [header, section, mrkdwn, actions, button]
 * @param username of the owner of the message to be shown on slack. Default is "owner"
 * @param text of the message to be sent. Can be a plain string or another object of [SlackText] which
 * must be converted to string JSON.
 * @param emoji confirms if emoji is included in the text.
 * @param style used when [SlackText] type is button to specify how to present buttons.
 * @param value used when [SlackText] type is button to specify the text to be shown.
 */
data class SlackText(
    @SerializedName("type") val type: String? = "mrkdwn",
    @SerializedName("username") val owner: String? = null,
    @SerializedName("text") val message: Any? = null,
    @SerializedName("emoji") val emoji: Boolean? = null,
    @SerializedName("style") val style: String? = null,
    @SerializedName("value") val value: String? = null,
    @SerializedName("fields") val fields: List<SlackText>? = null,
    @SerializedName("elements") val elements: List<SlackText>? = null
) {
    fun toJson(): String {
        val stringJson = getGson().toJson(this).toString()
        Log.d(javaClass.simpleName, stringJson)
        return stringJson
    }

    fun getGson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setExclusionStrategies(SuperclassExclusionStrategy())
            .addDeserializationExclusionStrategy(SuperclassExclusionStrategy())
            .addSerializationExclusionStrategy(SuperclassExclusionStrategy())
        return gsonBuilder.create()
    }

    companion object {
        fun headerText(message: String): SlackText {
            val headerText = SlackText(
                type = SlackType.HEADER.name.toLowerCase(Locale.getDefault()),
                message = SlackText(
                    type = SlackType.PLAIN_TEXT.name.toLowerCase(Locale.getDefault()),
                    message = message
                )
            )
            Log.d(javaClass.simpleName, headerText.toJson())
            return headerText
        }

        fun sectionText(vararg message: String, ): SlackText {
            val textList: MutableList<SlackText> = mutableListOf()
            message.forEach { text ->
                textList.add(
                    SlackText(
                        type = SlackType.MRKDWN.name.toLowerCase(Locale.getDefault()),
                        message = text
                    )
                )
            }
            return SlackText(
                type = SlackType.SECTION.name.toLowerCase(Locale.getDefault()),
                fields = textList
            )
        }

        fun buttonText(style: String, label: String): SlackText {
            return SlackText(
                type = SlackType.BUTTON.name.toLowerCase(Locale.getDefault()),
                message = SlackText(type = SlackType.PLAIN_TEXT.name.toLowerCase(Locale.getDefault()), message = label),
                style = style,
                value = label.trim().replace(" ", "")
            )
        }

        fun actionsText(vararg buttonMaps: Map<String, String>): SlackText {
            val buttonsList: MutableList<SlackText> = mutableListOf()
            buttonMaps.forEach { buttomMap: Map<String, String> ->
                val style = buttomMap.keys.first()
                val label = buttomMap.values.first()
                buttonsList.add(buttonText(style, label))
            }
            return SlackText(
                type = SlackType.ACTIONS.name.toLowerCase(Locale.getDefault()),
                elements = buttonsList
            )
        }

        fun simpleText(owner: String?, message: String): String {
            return SlackText(owner = owner, message = message).toJson()
        }
    }
}

/*
{
  "blocks": [
    {
      "text": {
        "emoji": false,
        "text": "Test Header",
        "type": "plain_text"
      },
      "type": "header",
      "emoji": false,
    }
  ]
}
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