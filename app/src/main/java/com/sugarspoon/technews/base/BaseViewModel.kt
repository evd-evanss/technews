package com.sugarspoon.technews.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<State, Routes> : ViewModel() {

    abstract val initialViewState: State

    private val internalState = MutableStateFlow(initialViewState)

    val state: MutableStateFlow<State>
        get() = internalState

    private var _state = MutableStateFlow(state)

    abstract fun navigateByRoute(routes: Routes)
}
