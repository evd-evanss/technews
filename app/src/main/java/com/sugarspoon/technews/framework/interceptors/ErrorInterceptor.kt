package com.sugarspoon.technews.framework.interceptors

import com.sugarspoon.technews.utils.NetworkErrorListener
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
        response.run {
            if(code == UNAUTHORIZED_CODE) {
                NetworkErrorListener.setEvent(response = response)
            }
        }
    }

    companion object {
        const val UNAUTHORIZED_CODE = 404
    }
}