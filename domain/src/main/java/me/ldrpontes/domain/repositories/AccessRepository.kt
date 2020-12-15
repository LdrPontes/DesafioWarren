package me.ldrpontes.domain.repositories

import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.DataResult

interface AccessRepository {
    suspend fun doLogin(email: String, password: String): DataResult<Access>
    suspend fun haveAccess(): DataResult<Access>
}