package me.ldrpontes.domain.usecase

import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.usecase.BaseUseCase
import me.ldrpontes.domain.repositories.GoalsRepository

class GetGoalsUseCase(private val repository: GoalsRepository):
    BaseUseCase<GetGoalsResponse, GetGoalsParams> {

    override suspend fun execute(params: GetGoalsParams): GetGoalsResponse {
        val result = repository.getGoals(params.token)

        return GetGoalsResponse(result)
    }
}

data class GetGoalsParams(val token: String)

data class GetGoalsResponse(val result: List<Goal>)
