package com.leoevg.san_dinner.data.googleFormsApi

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GoogleFormApi {
    @FormUrlEncoded
    @POST("forms/d/e/1FAIpQLSdQVbZMwH5_8Qd-3mj6Iplmkq3x-sFhBtn6L_GIHmkkPfNrkA/formResponse")
    suspend fun submitForm(
        @Field("entry.1162574950") name: String,
        @Field("entry.1789283155") workerId: String,
        @Field("entry.1155397463") selectedDishesNames: String
    ): Response<Unit>
}
