package me.ldrpontes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.ldrpontes.data.database.models.Goal

@Dao
interface GoalsDao {
    @Query("SELECT * FROM goals")
    suspend fun getAll(): Flow<List<Goal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg goal: Goal)
}