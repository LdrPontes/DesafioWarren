package me.ldrpontes.data.mappers

import me.ldrpontes.data.database.models.GoalDb
import me.ldrpontes.data.networking.models.GoalResponse
import me.ldrpontes.domain.entities.Background
import me.ldrpontes.domain.entities.Goal

class GoalsMapper : Mapper<Goal, GoalDb, GoalResponse>() {

    override fun mapDatabaseToDomain(data: GoalDb): Goal = Goal(
        data.id,
        data.name,
        data.totalBalance,
        data.goalAmount,
        data.goalDate,
        Background(
            data.background.thumb,
            data.background.small,
            data.background.full,
            data.background.regular,
            data.background.raw
        )
    )


    override fun mapNetworkingListToDatabaseList(data: List<GoalResponse>): List<GoalDb> {
        val list: ArrayList<GoalDb> = ArrayList()

        for (i in data) {
            list.add(mapNetworkingToDatabase(i))
        }

        return list
    }

    override fun mapDatabaseListToDomainList(data: List<GoalDb>): List<Goal> {
        val list: ArrayList<Goal> = ArrayList()

        for (i in data) {
            list.add(mapDatabaseToDomain(i))
        }

        return list
    }

    override fun mapNetworkingToDatabase(data: GoalResponse): GoalDb = GoalDb(
        data.id,
        data.name,
        data.totalBalance,
        data.goalAmount,
        data.goalDate,
        GoalDb.BackgroundDb(
            data.background.thumb,
            data.background.small,
            data.background.full,
            data.background.regular,
            data.background.raw
        )
    )


}