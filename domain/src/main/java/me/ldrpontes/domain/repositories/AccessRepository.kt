package me.ldrpontes.domain.repositories

import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.Result

interface AccessRepository {
    suspend fun doLogin(email: String, password: String): Result<Access>
    suspend fun haveAccess(): Result<Access>
}