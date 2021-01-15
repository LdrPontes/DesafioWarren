package me.ldrpontes.data.repositories

import me.ldrpontes.data.database.dao.AccessDao
import me.ldrpontes.data.database.models.AccessDb
import me.ldrpontes.data.mappers.AccessMapper
import me.ldrpontes.data.mappers.Mapper
import me.ldrpontes.data.networking.failure
import me.ldrpontes.data.networking.isResponseSuccessful
import me.ldrpontes.data.networking.models.AccessBody
import me.ldrpontes.data.networking.models.AccessResponse
import me.ldrpontes.data.networking.services.AccessService
import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.repositories.AccessRepository
import java.lang.Exception

class AccessRepositoryImpl(
    private val accessService: AccessService,
    private val accessDao: AccessDao,
    private val accessMapper: AccessMapper
) : AccessRepository {

    override suspend fun doLogin(email: String, password: String): DataResult<Access> {
        try {
            val response = accessService.doLogin(AccessBody(email, password))

            return if (response.isResponseSuccessful()) {

                accessDao.saveAccess(accessMapper.mapNetworkingToDatabase(response.body()!!))

                DataResult.Success<Access>(
                    accessMapper.mapNetworkingToDomain(response.body()!!)
                )
            } else {
                DataResult.Failure(response.failure()!!)
            }
        } catch (e: Exception) {
            return DataResult.Failure(e)
        }

    }

    override suspend fun haveAccess(): DataResult<Access> {
        return try {
            val response = accessDao.getAccess()

            DataResult.Success(accessMapper.mapDatabaseToDomain(response))

        } catch (e: Exception) {
            DataResult.Failure(e)
        }

    }

}