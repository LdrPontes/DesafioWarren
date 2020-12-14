package me.ldrpontes.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.GoalsRepository
import me.ldrpontes.domain.entities.Result

class GetGoalsUseCase(private val repository: GoalsRepository) :
    BaseUseCase<Flow<Result<List<Goal>>>, GetGoalsParams> {

    override suspend fun execute(params: GetGoalsParams): Flow<Result<List<Goal>>> {
        val result = repository.getGoals(params.token)

        return result.distinctUntilChanged()
    }
}

data class GetGoalsParams(val token: String)

