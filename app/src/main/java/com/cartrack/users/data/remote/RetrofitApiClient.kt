package com.cartrack.users.data.remote

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiClient private constructor(){
    private val API_BASE_URL = "https://jsonplaceholder/"
    val retrofit: Retrofit

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
           // .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

        retrofit = builder.build()
    }

    companion object {
        private var self: RetrofitApiClient? = null

        val instance: RetrofitApiClient
            get() {
                if (self == null) {
                    synchronized(RetrofitApiClient::class.java) {
                        if (self == null) {
                            self =
                                RetrofitApiClient()
                        }
                    }
                }
                return self!!
            }

    }
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

}