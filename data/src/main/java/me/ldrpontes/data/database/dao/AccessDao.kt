package me.ldrpontes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ldrpontes.data.database.models.Access

@Dao
interface AccessDao {
    @Query("SELECT * FROM access LIMIT 1")
    suspend fun getAccess(): Access

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg access: Access)
}