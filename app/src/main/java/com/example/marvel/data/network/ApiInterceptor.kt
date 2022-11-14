package com.example.marvel.data.network

import com.example.marvel.presentation.utils.PRIV_KEY
import com.example.marvel.presentation.utils.PUB_KEY
import com.example.marvel.presentation.utils.TS
import com.example.marvel.presentation.utils.md5Generator
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiInterceptor  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("ts", TS)
            .addQueryParameter("apikey", PUB_KEY)
            .addQueryParameter("hash", md5Generator(TS + PRIV_KEY + PUB_KEY))
            .build()
        val request: Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}