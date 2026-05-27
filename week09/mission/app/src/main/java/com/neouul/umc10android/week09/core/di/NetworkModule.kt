package com.neouul.umc10android.week09.core.di

import com.google.gson.Gson
import com.neouul.umc10android.week09.data.data_source.remote.api.ProductService
import com.neouul.umc10android.week09.data.data_source.remote.api.ReqResService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val REQRES_BASE_URL = "https://reqres.in/"
    private const val PRODUCT_BASE_URL = "https://raw.githubusercontent.com/Neouul/umc-mock-data/main/"

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideReqResService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ReqResService {
        return Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ReqResService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ProductService {
        return Retrofit.Builder()
            .baseUrl(PRODUCT_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ProductService::class.java)
    }
}
