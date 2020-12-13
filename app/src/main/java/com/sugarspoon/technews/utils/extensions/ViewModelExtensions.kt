package com.sugarspoon.technews.utils.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> ViewModel.collectFrom(flow: Flow<T>, result: (T) -> Unit) {
    with(viewModelScope) {
        launch {
            flow.onCollect(
                onSuccess = {
                    result(it)
                }
            )
        }
    }
}
