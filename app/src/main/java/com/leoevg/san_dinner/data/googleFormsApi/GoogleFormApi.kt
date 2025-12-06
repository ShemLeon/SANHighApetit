package com.leoevg.san_dinner.data.googleFormsApi

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GoogleFormApi {
    @FormUrlEncoded
    @POST("forms/u/0/d/e/1FAIpQLSfDPadKqzUgB7XcPaXmog5y4jC4sE4B21Tvv8GxvNva8_9gsw/formResponse?&submit=Submit")
    suspend fun submitForm(
        @Field("entry.2014596361") name: String,
        @Field("entry.353467567") workerId: String,
        @Field("entry.184144520") selectedDishesNames: String
    ): Response<Unit>
}