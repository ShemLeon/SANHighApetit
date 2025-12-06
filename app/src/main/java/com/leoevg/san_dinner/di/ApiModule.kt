package com.leoevg.san_dinner.di

import com.leoevg.san_dinner.data.googleFormsApi.GoogleFormApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://docs.google.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleFormApi(retrofit: Retrofit) : GoogleFormApi {
        return retrofit.create(GoogleFormApi::class.java)
    }
}