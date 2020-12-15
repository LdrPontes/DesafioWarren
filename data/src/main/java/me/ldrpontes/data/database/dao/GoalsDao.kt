package me.ldrpontes.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.ldrpontes.data.database.models.GoalDb

@Dao
interface GoalsDao {
    @Transaction
    suspend fun saveGoals(goals: List<GoalDb>){
        deleteAll()
        insertAll(goals)
    }

    @Query("SELECT * FROM goals")
    fun getAll(): Flow<List<GoalDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(goals: List<GoalDb>)

    @Query("DELETE FROM goals")
    suspend fun deleteAll()
}