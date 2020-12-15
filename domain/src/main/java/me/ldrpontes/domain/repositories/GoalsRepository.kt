package me.ldrpontes.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.entities.DataResult

interface GoalsRepository {
    suspend fun getGoals(token: String): Flow<DataResult<List<Goal>>>
}