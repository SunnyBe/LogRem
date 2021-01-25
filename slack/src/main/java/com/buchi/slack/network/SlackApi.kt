package com.buchi.slack.network

import com.buchi.slack.BuildConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface SlackApi {

    @FormUrlEncoded
    @POST(BuildConfig.SLACKIFY_STATUS_HOOK)
    fun postSlackMessage(
        @Field("payload")
        slackNotificationMessage: String
    ): Call<ResponseBody>
}