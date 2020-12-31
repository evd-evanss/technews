package com.sugarspoon.data

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        handleResponseCode(response)
        return response
    }

    private fun handleResponseCode(response: Response) {
        if(response.code == UNAUTHORIZED_CODE) {
            NetworkErrorListener.setEvent(error = true)
        } else {
            NetworkErrorListener.setEvent(error = false)
        }
    }

    companion object {
        const val UNAUTHORIZED_CODE = 401
    }
}