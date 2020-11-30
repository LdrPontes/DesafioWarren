package me.ldrpontes.domain.generics

interface BaseUseCase<T, Params> {
    suspend fun execute(params: Params): T
}