package com.example.pawpatrol.api

import android.content.Context
import com.example.pawpatrol.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    context: Context
) : Interceptor {

    private val sessionManager =
        SessionManager(context)

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val token =
            sessionManager.getToken()

        val request =
            chain.request()
                .newBuilder()

        token?.let {

            request.addHeader(
                "Authorization",
                "Bearer $it"
            )

        }

        return chain.proceed(
            request.build()
        )
    }
}