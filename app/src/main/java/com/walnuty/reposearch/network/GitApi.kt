package com.walnuty.reposearch.network

import com.walnuty.reposearch.common.ShareData
import com.walnuty.reposearch.data.response.ResponseRepos
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    @GET("repositories")
    suspend fun searchRepositoryByKeyword(
        @Query("keyword") keyword: String
    ): ResponseRepos

    companion object {
        private val BASE_URL = ShareData.BASE_URL
        fun create(): GitApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
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