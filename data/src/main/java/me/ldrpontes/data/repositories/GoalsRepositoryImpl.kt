package me.ldrpontes.data.repositories

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import me.ldrpontes.data.database.dao.GoalsDao
import me.ldrpontes.data.database.models.GoalDb
import me.ldrpontes.data.mappers.GoalsMapper
import me.ldrpontes.data.networking.getResult
import me.ldrpontes.data.networking.models.GoalResponse
import me.ldrpontes.data.networking.models.ListGoalsResponse
import me.ldrpontes.data.networking.services.GoalsService
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.GoalsRepository

class GoalsRepositoryImpl(private val goalsService: GoalsService, private val goalsDao: GoalsDao, private val goalsMapper: GoalsMapper) : GoalsRepository {

    override suspend fun getGoals(token: String): Flow<List<Goal>> {

        getGoalsFromApi(token)

        return flow {
            goalsDao.getAll().collect {
                emit(goalsMapper.mapDatabaseListToDomainList(it))
            }
        }
    }

    private suspend fun getGoalsFromApi(token: String){
        goalsService.getAll(token).getResult(onSuccess = {

            goalsDao.saveGoals(goalsMapper.mapNetworkingListToDatabaseList(it.portfolios))

        }, onFailure = {
            //Not Implemented yet
        })
    }
}