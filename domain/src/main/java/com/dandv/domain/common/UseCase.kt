package com.dandv.domain.common

/**
 * A use case is a lists of actions, defines the interactions between layers to achieve a goal
 */
interface UseCase<out Type, in Params> where Type : Any {
    suspend fun buildUseCase(params: Params): Type
}