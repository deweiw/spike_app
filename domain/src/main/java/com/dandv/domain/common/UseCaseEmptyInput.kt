package com.dandv.domain.common

interface UseCaseEmptyInput<out Type> where Type : Any {
    suspend fun buildUseCase(): Type
}