package me.ldrpontes.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import me.ldrpontes.data.database.dao.GoalsDao
import me.ldrpontes.data.mappers.GoalsMapper
import me.ldrpontes.data.networking.handle
import me.ldrpontes.data.networking.services.GoalsService
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.GoalsRepository
import me.ldrpontes.domain.entities.Result

class GoalsRepositoryImpl(
    private val goalsService: GoalsService,
    private val goalsDao: GoalsDao,
    private val goalsMapper: GoalsMapper
) : GoalsRepository {

    override suspend fun getGoals(token: String): Flow<Result<List<Goal>>> {

        return flow {

            goalsDao.getAll().collect {
                if (!it.isNullOrEmpty())
                    emit(Result.Success(goalsMapper.mapDatabaseListToDomainList(it)))
            }

            goalsService.getAll(token).handle(
                onSuccess = {
                    goalsDao.saveGoals(goalsMapper.mapNetworkingListToDatabaseList(it.portfolios))
                },
                onFailure = {
                    emit(Result.Failure(it))
                }
            )

        }

    }

}