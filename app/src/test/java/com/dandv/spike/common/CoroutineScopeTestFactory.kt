package com.dandv.spike.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CoroutineScopeTestFactory : CoroutineScopeFactory() {

    override fun getScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Unconfined)
    }
}