package me.ldrpontes.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.entities.Result

interface GoalsRepository {
    suspend fun getGoals(token: String): Flow<Result<List<Goal>>>
}