package com.walnuty.reposearch.network

import com.walnuty.reposearch.common.ShareData
import com.walnuty.reposearch.data.response.ResponseRepos
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    @GET("repositories")
    suspend fun searchRepositoryByKeyword(
        @Query("q") keyword: String
    ): ResponseRepos

    companion object {
        private val BASE_URL = ShareData.BASE_URL
        fun create(): GitApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-type", "application/json")
                    .build()

                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitApi::class.java)
        }
    }
}