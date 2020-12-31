package com.sugarspoon.technews.utils

import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Response

object NetworkErrorListener {

    data class EventError(
        val hasError: MutableStateFlow<Boolean> = MutableStateFlow(false),
        val code: MutableStateFlow<Int?> = MutableStateFlow(null),
        val message: MutableStateFlow<String?> = MutableStateFlow(null),
        val success: Int = 200
    )

    private val eventError = EventError()

    fun getEvents(): EventError {
        return eventError
    }

    fun setEvent(response: Response) {
        response.run {
            eventError.code.value = code
            eventError.message.value = message
            eventError.hasError.value = code != eventError.success
        }
    }
}