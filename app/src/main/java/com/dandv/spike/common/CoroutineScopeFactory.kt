package com.dandv.spike.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class CoroutineScopeFactory {

    private val job: Job by lazy {
        Job()
    }

    open fun getScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main + job)
    }
}