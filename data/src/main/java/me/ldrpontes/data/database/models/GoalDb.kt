package me.ldrpontes.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.ldrpontes.domain.entities.Background
import me.ldrpontes.domain.entities.Goal

@Entity(tableName = "goals")
data class GoalDb(

    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "total_balance") val totalBalance: Double,
    @ColumnInfo(name = "goal_amount") val goalAmount: Double?,
    @ColumnInfo(name = "goal_date") val goalDate: String,
    @Embedded val background: BackgroundDb

) : DomainMapper<Goal> {

    data class BackgroundDb(
        val thumb: String,
        val small: String,
        val full: String,
        val regular: String,
        val raw: String
    )

    override fun mapToDomain(): Goal = Goal(
        id,
        name,
        totalBalance,
        goalAmount,
        goalDate,
        Background(
            background.thumb,
            background.small,
            background.full,
            background.regular,
            background.raw
        )
    )

}