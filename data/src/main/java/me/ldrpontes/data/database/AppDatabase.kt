package me.ldrpontes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.ldrpontes.data.database.dao.AccessDao
import me.ldrpontes.data.database.dao.GoalsDao
import me.ldrpontes.data.database.models.Access
import me.ldrpontes.data.database.models.Goal


@Database(entities = [Goal::class, Access::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalsDao(): GoalsDao
    abstract fun accessDao(): AccessDao
}