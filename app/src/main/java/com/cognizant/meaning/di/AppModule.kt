package com.cognizant.meaning.di

import android.content.Context
import android.util.Log
import com.cognizant.common.Constants
import com.cognizant.common.NetworkConnectionInterceptor
import com.cognizant.meaning.data.remote.MeaningApi
import com.cognizant.meaning.data.repository.MeaningRepositoryImpl
import com.cognizant.meaning.domain.repository.MeaningRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideApi(client: OkHttpClient): MeaningApi {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeaningApi::class.java)
    }


    @Provides
    @Singleton
    fun getMeaningRepository(api: MeaningApi): MeaningRepository {
        return MeaningRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesNetworkInterceptor(@ApplicationContext context: Context) =
        NetworkConnectionInterceptor(context)


    @Provides
    @Singleton
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor {
            Log.i("MeaningNetwork", it)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        hli: HttpLoggingInterceptor,
        nci: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(nci)
            .addInterceptor(hli)
            .build()
    }
}