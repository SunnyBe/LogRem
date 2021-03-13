package com.buchi.slack.entity

sealed class SlackEntity {
    data class SlackMessage(
        val owner: String,
        val text: String?
    ): SlackEntity() {
        override fun toString(): String {
            return ("{"
                    + "'username': '$owner',"
                    + "'text':' " + text + " "
                    + "' "
                    + "}")
        }
    }

    data class SlackUploadResponse(
        val successful: Boolean? = false,
        val response: String? = ""
    ): SlackEntity()
}