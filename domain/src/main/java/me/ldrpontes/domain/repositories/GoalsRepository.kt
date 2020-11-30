package me.ldrpontes.domain.repositories

import me.ldrpontes.domain.entities.Goal

interface GoalsRepository {
    suspend fun getGoals(token: String): List<Goal>
}