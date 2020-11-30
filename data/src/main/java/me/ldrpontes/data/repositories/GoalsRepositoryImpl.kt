package me.ldrpontes.data.repositories

import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.GoalsRepository

class GoalsRepositoryImpl(): GoalsRepository {

    override suspend fun getGoals(token: String): List<Goal> {
        TODO()
    }
}