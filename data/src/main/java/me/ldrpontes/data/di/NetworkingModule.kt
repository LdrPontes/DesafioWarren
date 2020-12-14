package me.ldrpontes.data.di

import me.ldrpontes.data.networking.services.AccessService
import me.ldrpontes.data.networking.services.GoalsService
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://enigmatic-bayou-48219.herokuapp.com/api/v2/"

val networkingModule = module {
    single { GsonConverterFactory.create() as Converter.Factory }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }

    single {
        get<Retrofit>().create(GoalsService::class.java)
    }

    single {
        get<Retrofit>().create(AccessService::class.java)
    }
}