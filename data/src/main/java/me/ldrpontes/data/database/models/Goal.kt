package me.ldrpontes.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "total_balance") var totalBalance: Double,
    @ColumnInfo(name = "goal_amount") var goalAmount: Double?,
    @ColumnInfo(name = "goal_date") var goalDate: String
)