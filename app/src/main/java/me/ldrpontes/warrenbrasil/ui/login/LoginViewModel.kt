package me.ldrpontes.warrenbrasil.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import me.ldrpontes.domain.usecase.LoginUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase, application: Application) : AndroidViewModel(application) {

}