package me.ldrpontes.domain.repositories

import me.ldrpontes.domain.entities.Access

interface AuthRepository {
    suspend fun doLogin(email: String, password: String): Access
}