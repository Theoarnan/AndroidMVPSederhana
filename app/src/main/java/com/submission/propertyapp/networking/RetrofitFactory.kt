package com.submission.propertyapp.networking

import android.content.Context
import com.submission.propertyapp.util.Preferences
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    val host = "192.168.43.219"
    val BASE_URL = "http://$host/ws_property_android/index.php/api/v1/"
    fun create() : RetrofitService{
        var builder = OkHttpClient().newBuilder()
        builder.connectTimeout(15,TimeUnit.SECONDS)

//        var logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.HEADERS
//        logging.level = HttpLoggingInterceptor.Level.BODY

        val retrofit = Retrofit.Builder()
            .client(builder.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(RetrofitService::class.java)
    }

    fun create(context: Context) : RetrofitService{
        val token = Preferences.getToken(context)
        var builder = OkHttpClient().newBuilder()
        builder.connectTimeout(15,TimeUnit.SECONDS)

        //add Token
        builder.addInterceptor{ chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("token", token).build()
            chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
            .client(builder.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
}
