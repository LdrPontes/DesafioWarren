package me.ldrpontes.data.di

import androidx.room.Room
import me.ldrpontes.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "warren-database"
        ).build()
    }
}