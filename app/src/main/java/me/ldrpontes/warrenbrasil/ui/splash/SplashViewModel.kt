package me.ldrpontes.warrenbrasil.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.usecase.GetAccessUseCase

class SplashViewModel(private val getAccessUseCase: GetAccessUseCase, application: Application) :
    AndroidViewModel(application) {

    val accessState: LiveData<Boolean> = liveData {
        val result = getAccess()
        delay(2000)
        emit(result)
    }

    private suspend fun getAccess(): Boolean {

        val result = getAccessUseCase.execute()

        return result is DataResult.Success
    }

}