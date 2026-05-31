package com.neouul.umc10android.week09.core.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.neouul.umc10android.week09.BuildConfig
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
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val REQRES_BASE_URL = "https://reqres.in/"
    private const val PRODUCT_BASE_URL = "https://raw.githubusercontent.com/Neouul/umc-mock-data/main/"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ReqResClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ProductClient

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor =
            HttpLoggingInterceptor { message ->
                when {
                    !message.isJsonObject() && !message.isJsonArray() ->
                        Log.d("RETROFIT", "CONNECTION INFO -> $message")
                    else ->
                        try {
                            Log.d(
                                "RETROFIT",
                                GsonBuilder().setPrettyPrinting().create().toJson(
                                    JsonParser().parse(message),
                                ),
                            )
                        } catch (m: JsonSyntaxException) {
                            Log.d("RETROFIT", message)
                        }
                }
            }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    private fun String.isJsonObject(): Boolean = startsWith("{") && endsWith("}")
    private fun String.isJsonArray(): Boolean = startsWith("[") && endsWith("]")

    @Provides
    @Singleton
    fun provideBaseOkHttpClient(
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
    @ReqResClient
    fun provideReqResOkHttpClient(
        baseOkHttpClient: OkHttpClient
    ): OkHttpClient {
        return baseOkHttpClient.newBuilder()
            .addInterceptor { chain ->
                val apiKey = try { BuildConfig.REQRES_API_KEY } catch (e: Exception) { null }
                Log.d("NetworkModule", "ReqRes request detected. API Key exists: ${apiKey != null}")
                val request = chain.request()
                val newRequest = if (apiKey != null && apiKey.isNotBlank()) {
                    request.newBuilder()
                        .addHeader("x-api-key", apiKey)
                        .build()
                } else {
                    request
                }
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    @ProductClient
    fun provideProductOkHttpClient(
        baseOkHttpClient: OkHttpClient
    ): OkHttpClient {
        return baseOkHttpClient.newBuilder().build()
    }

    @Provides
    @Singleton
    fun provideReqResService(
        @ReqResClient okHttpClient: OkHttpClient,
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
        @ProductClient okHttpClient: OkHttpClient,
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
