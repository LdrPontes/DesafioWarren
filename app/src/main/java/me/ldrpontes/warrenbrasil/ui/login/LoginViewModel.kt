package me.ldrpontes.warrenbrasil.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ldrpontes.data.utils.HttpError
import me.ldrpontes.domain.entities.Access
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.entities.onFailure
import me.ldrpontes.domain.entities.onSuccess
import me.ldrpontes.domain.usecase.GetAccessUseCase
import me.ldrpontes.domain.usecase.LoginParams
import me.ldrpontes.domain.usecase.LoginUseCase
import me.ldrpontes.warrenbrasil.App
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.utils.State
import java.lang.Exception

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    application: Application
) :
    AndroidViewModel(application) {

    var email: String = ""
    var password: String = ""
    val loginState: MutableLiveData<State<Access>> = MutableLiveData()


    fun doLogin() = viewModelScope.launch {
        loginState.value = State.Loading(true)

        loginUseCase.execute(LoginParams(email, password))
            .onSuccess {
                loginState.value = State.Success(it)
                loginState.value = State.Loading(false)
            }
            .onFailure {
                responseErrorHandler(it)
                loginState.value = State.Loading(false)
            }
    }

    private fun responseErrorHandler(exception: Exception) {
        if (exception is HttpError)
            when (exception.code) {
                400 -> {
                    loginState.value =
                        State.Failure(getApplication<App>().getString(R.string.bad_request_error))
                }
                else -> {
                    loginState.value =
                        State.Failure(getApplication<App>().getString(R.string.general_error))
                }
            }
        else
            loginState.value =
                State.Failure(getApplication<App>().getString(R.string.general_error))

    }
}