package com.example.modernrecipes.di

import com.example.modernrecipes.util.Constants.Companion.BASE_URL
import com.example.modernrecipes.data.network.FoodRecipeAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient{
         return OkHttpClient.Builder()
             .readTimeout(15,TimeUnit.SECONDS)
             .connectTimeout(15, TimeUnit.SECONDS)
             .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient : OkHttpClient,
        gsonConverterFactory: GsonConverterFactory

    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): FoodRecipeAPI {
        return retrofit.create(FoodRecipeAPI::class.java)
    }
}