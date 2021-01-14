package me.ldrpontes.warrenbrasil.di

import me.ldrpontes.domain.usecase.GetAccessUseCase
import me.ldrpontes.domain.usecase.GetGoalsUseCase
import me.ldrpontes.domain.usecase.LoginUseCase
import me.ldrpontes.warrenbrasil.ui.goals.GoalViewModel
import me.ldrpontes.warrenbrasil.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { GetGoalsUseCase(get()) }

    single { GetAccessUseCase(get()) }

    single { LoginUseCase(get()) }


    viewModel { GoalViewModel(get(), get(), get()) }

    viewModel { LoginViewModel(get(), get()) }

}