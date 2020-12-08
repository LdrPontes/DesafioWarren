package me.ldrpontes.data.repositories

import kotlinx.coroutines.flow.Flow
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.GoalsRepository

class GoalsRepositoryImpl(): GoalsRepository {

    override suspend fun getGoals(token: String): Flow<List<Goal>> {
        TODO()
    }
}