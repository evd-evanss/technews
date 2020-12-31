package com.sugarspoon.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object NetworkErrorListener {

    private val error = MutableStateFlow(false)

    fun getEvents(): Flow<Boolean> {
        return error
    }

    fun setEvent(error: Boolean) {
        NetworkErrorListener.error.value = error
    }
}