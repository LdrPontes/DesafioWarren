package me.ldrpontes.data.repositories

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import me.ldrpontes.data.database.dao.GoalsDao
import me.ldrpontes.data.database.models.GoalDb
import me.ldrpontes.data.mappers.GoalsMapper
import me.ldrpontes.data.networking.handle
import me.ldrpontes.data.networking.services.GoalsService
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.GoalsRepository
import java.lang.Exception

class GoalsRepositoryImpl(
    private val goalsService: GoalsService,
    private val goalsDao: GoalsDao,
    private val goalsMapper: GoalsMapper
) : GoalsRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getGoals(token: String): Flow<DataResult<List<Goal>>> {

        val getGoalsRemote = flow {
            try {
                goalsService.getAll(token).handle(
                    onSuccess = {
                        goalsDao.saveGoals(goalsMapper.mapNetworkingListToDatabaseList(it.portfolios))
                    },
                    onFailure = {
                        emit(DataResult.Failure(it))
                    }
                )
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }

        val getGoalsLocal = goalsDao.getAll().map {
            DataResult.Success(goalsMapper.mapDatabaseListToDomainList(it))
        }

        return merge(
            getGoalsRemote,
            getGoalsLocal
        )
    }
}