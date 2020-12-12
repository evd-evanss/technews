package com.sugarspoon.technews.utils.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

fun <T> Flow<T>.onCollect(
    onSuccess: (suspend (t: T) -> Unit)? = null,
    onError: ((e: Throwable) -> Unit)? = null,
    onLoading: ((loading: Boolean) -> Unit)? = null,
    coroutineScope: CoroutineScope = CoroutineScope(IO)
) {
    coroutineScope.launch {
        CoroutineScope(Main).launch {
            onLoading?.let { loading ->
                loading(true)
            }
        }
        try {
            collect { result ->
                onSuccess?.let {
                    CoroutineScope(Main).launch {
                        it(result)
                        onLoading?.let { loading ->
                            loading(false)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            onError?.let {
                CoroutineScope(Main).launch {
                    it(e)
                    onLoading?.let { loading ->
                        loading(false)
                    }
                }
            }
        }
    }
}
