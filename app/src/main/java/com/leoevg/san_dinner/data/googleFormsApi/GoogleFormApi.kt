package com.leoevg.san_dinner.data.googleFormsApi

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GoogleFormApi {
    @FormUrlEncoded
    @POST("forms/u/0/d/e/1FAIpQLSfDPadKqzUgB7XcPaXmog5y4jC4sE4B21Tvv8GxvNva8_9gsw/formResponse?&submit=Submit")
    suspend fun submitForm(
        @Field("entry.499752462") name: String,
        @Field("entry.667986601") workerId: String,
        @Field("entry.184144520") selectedDishesNames: String
    ): Response<Unit>
}

/* Оригинальные параметры SAN
 @POST("forms/d/e/1FAIpQLSdQVbZMwH5_8Qd-3mj6Iplmkq3x-sFhBtn6L_GIHmkkPfNrkA/formResponse")
    suspend fun submitForm(
        @Field("entry.1162574950") name: String,
        @Field("entry.1789283155") workerId: String,
        @Field("entry.1155397463") selectedDishesNames: String
 */

/*
тестовые параметры
 @POST("forms/u/0/d/e/1FAIpQLSfDPadKqzUgB7XcPaXmog5y4jC4sE4B21Tvv8GxvNva8_9gsw/formResponse?&submit=Submit")
    suspend fun submitForm(
        @Field("entry.2014596361") name: String,
        @Field("entry.353467567") workerId: String,
        @Field("entry.184144520") selectedDishesNames: String
    ): Response<Unit>
 */
