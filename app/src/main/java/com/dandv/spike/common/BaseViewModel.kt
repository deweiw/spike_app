package com.dandv.spike.common

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.cancel

open class BaseViewModel(coroutineScopeFactory: CoroutineScopeFactory) : ViewModel() {

    val coroutineScope = coroutineScopeFactory.getScope()

    public override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}