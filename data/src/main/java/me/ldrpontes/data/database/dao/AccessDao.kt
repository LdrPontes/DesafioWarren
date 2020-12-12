package me.ldrpontes.data.database.dao

import androidx.room.*
import me.ldrpontes.data.database.models.AccessDb

@Dao
interface AccessDao {

    @Transaction
    suspend fun saveAccess(access: AccessDb){
        deleteAll()
        insert(access)
    }

    @Query("SELECT * FROM access LIMIT 1")
    suspend fun getAccess(): AccessDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(access: AccessDb)

    @Query("DELETE FROM access")
    suspend fun deleteAll()
}