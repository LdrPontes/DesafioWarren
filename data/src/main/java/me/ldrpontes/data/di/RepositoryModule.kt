package me.ldrpontes.data.di

import me.ldrpontes.data.database.models.AccessDb
import me.ldrpontes.data.database.models.GoalDb
import me.ldrpontes.data.mappers.AccessMapper
import me.ldrpontes.data.mappers.GoalsMapper
import me.ldrpontes.data.mappers.Mapper
import me.ldrpontes.data.networking.models.AccessResponse
import me.ldrpontes.data.networking.models.GoalResponse
import me.ldrpontes.data.repositories.AccessRepositoryImpl
import me.ldrpontes.data.repositories.GoalsRepositoryImpl
import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.domain.repositories.AccessRepository
import me.ldrpontes.domain.repositories.GoalsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<Mapper<Goal, GoalDb, GoalResponse>> { GoalsMapper() }

    single<Mapper<Access, AccessDb, AccessResponse>> { AccessMapper() }

    factory<GoalsRepository> { GoalsRepositoryImpl(get(), get(), get()) }

    factory<AccessRepository> { AccessRepositoryImpl(get(), get(), get()) }
}