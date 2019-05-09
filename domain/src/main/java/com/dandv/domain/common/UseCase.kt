package com.dandv.domain.common

interface UseCase<out Type, in Params> where Type : Any {
    suspend fun buildUseCase(params: Params): Type
}