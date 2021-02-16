package com.buchi.slack.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url


interface SlackApi {

    @FormUrlEncoded
    @POST
    fun postSlackMessage(
        @Url url: String,
        @Field("payload")
        slackNotificationMessage: String
    ): Call<ResponseBody>
}