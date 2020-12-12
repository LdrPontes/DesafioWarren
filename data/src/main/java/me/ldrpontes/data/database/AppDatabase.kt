package me.ldrpontes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.ldrpontes.data.database.dao.AccessDao
import me.ldrpontes.data.database.dao.GoalsDao
import me.ldrpontes.data.database.models.AccessDb
import me.ldrpontes.data.database.models.GoalDb


@Database(entities = [GoalDb::class, AccessDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalsDao(): GoalsDao
    abstract fun accessDao(): AccessDao
}