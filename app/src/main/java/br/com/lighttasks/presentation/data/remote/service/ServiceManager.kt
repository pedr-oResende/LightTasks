package br.com.lighttasks.presentation.data.remote.service

import br.com.lighttasks.BuildConfig
import br.com.lighttasks.presentation.data.remote.converter.ToStringConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class ServiceManager(baseUrl: String) {

    private val retrofit: Retrofit

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ToStringConverterFactory())
            .build()
    }

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)
}