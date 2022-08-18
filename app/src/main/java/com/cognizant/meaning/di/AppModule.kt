package com.cognizant.meaning.di

import android.util.Log
import com.cognizant.common.Constants
import com.cognizant.meaning.data.remote.MeaningApi
import com.cognizant.meaning.data.repository.MeaningRepositoryImpl
import com.cognizant.meaning.domain.repository.MeaningRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): MeaningApi {
        val loggingInterceptor = HttpLoggingInterceptor {
            Log.i("MeaningNetwork", it)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeaningApi::class.java)
    }


    @Provides
    @Singleton
    fun getMeaningRepository(api: MeaningApi): MeaningRepository {
        return MeaningRepositoryImpl(api)
    }
}