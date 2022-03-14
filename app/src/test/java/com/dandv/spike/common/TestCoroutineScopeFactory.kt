package com.dandv.spike.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class TestCoroutineScopeFactory : CoroutineScopeFactory() {

    override fun getScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Unconfined)
    }
}
