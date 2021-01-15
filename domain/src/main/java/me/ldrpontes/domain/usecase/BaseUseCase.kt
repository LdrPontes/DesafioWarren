package me.ldrpontes.domain.usecase

interface BaseUseCase<out T, in Params> {
    suspend fun execute(params: Params): T
}

interface BaseUseCaseNoParams<out T> {
    suspend fun execute(): T
}