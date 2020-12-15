package me.ldrpontes.data.repositories

import me.ldrpontes.data.database.dao.AccessDao
import me.ldrpontes.data.database.models.AccessDb
import me.ldrpontes.data.mappers.Mapper
import me.ldrpontes.data.networking.failure
import me.ldrpontes.data.networking.handle
import me.ldrpontes.data.networking.isResponseSuccessful
import me.ldrpontes.data.networking.models.AccessBody
import me.ldrpontes.data.networking.models.AccessResponse
import me.ldrpontes.data.networking.services.AccessService
import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.Result
import me.ldrpontes.domain.repositories.AccessRepository
import java.lang.Exception
import kotlin.coroutines.suspendCoroutine

class AccessRepositoryImpl(
    private val accessService: AccessService,
    private val accessDao: AccessDao,
    private val accessMapper: Mapper<Access, AccessDb, AccessResponse>
) : AccessRepository {

    override suspend fun doLogin(email: String, password: String): Result<Access> {

        val response = accessService.doLogin(AccessBody(email, password))

        return if (response.isResponseSuccessful()) {

            accessDao.saveAccess(accessMapper.mapNetworkingToDatabase(response.body()!!))

            Result.Success<Access>(
                accessMapper.mapNetworkingToDomain(response.body()!!)
            )
        } else {
            Result.Failure(response.failure()!!)
        }
    }

    override suspend fun haveAccess(): Result<Access> {
        return try {
            val response = accessDao.getAccess()

            Result.Success(accessMapper.mapDatabaseToDomain(response))

        } catch (e: Exception) {
            Result.Failure(e)
        }

    }

}