package me.ldrpontes.warrenbrasil.ui.goals

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import me.ldrpontes.domain.entities.*
import me.ldrpontes.domain.usecase.GetAccessUseCase
import me.ldrpontes.domain.usecase.GetGoalsParams
import me.ldrpontes.domain.usecase.GetGoalsUseCase
import me.ldrpontes.warrenbrasil.utils.State
import me.ldrpontes.warrenbrasil.utils.onFailure
import me.ldrpontes.warrenbrasil.utils.onSuccess
import java.io.IOException

class GoalViewModel(
    private val getAccessUseCase: GetAccessUseCase,
    private val getGoalsUseCase: GetGoalsUseCase,
    application: Application
) : AndroidViewModel(application) {


    val goals: LiveData<State<List<Goal>>> =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            getGoalsHandler(this)
        }

    private suspend fun getGoalsHandler(scope: LiveDataScope<State<List<Goal>>>) {
        scope.emit(State.Loading(true))

        getAccess()
            .onSuccess {
                getGoals(it.accessToken).collect { result ->
                    result
                        .onSuccess { data ->
                            scope.emit(State.Success(data))
                            scope.emit(State.Loading(false))
                        }
                        .onFailure { exception ->
                            scope.emit(State.Failure(exception.message!!))
                            scope.emit(State.Loading(false))
                        }
                }

            }
            .onFailure {
                scope.emit(State.Loading(false))
                scope.emit(State.Failure(it))
            }
    }

    private suspend fun getAccess(): State<Access> {

        val result = getAccessUseCase.execute()

        return if (result is DataResult.Success) {
            State.Success(result.data)
        } else {
            State.Failure("Sem acesso")
        }
    }

    private suspend fun getGoals(token: String): Flow<DataResult<List<Goal>>> {
        return getGoalsUseCase.execute(GetGoalsParams(token))
    }

}