package me.ldrpontes.warrenbrasil.ui.goals

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.ldrpontes.data.utils.HttpError
import me.ldrpontes.domain.entities.*
import me.ldrpontes.domain.usecase.GetAccessUseCase
import me.ldrpontes.domain.usecase.GetGoalsParams
import me.ldrpontes.domain.usecase.GetGoalsUseCase
import me.ldrpontes.warrenbrasil.App
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.utils.State
import me.ldrpontes.warrenbrasil.utils.onFailure
import me.ldrpontes.warrenbrasil.utils.onSuccess
import java.io.IOException
import java.lang.Exception

class GoalViewModel(
    private val getAccessUseCase: GetAccessUseCase,
    private val getGoalsUseCase: GetGoalsUseCase,
    application: Application
) : AndroidViewModel(application) {


    var selectedGoal: Goal? = null
    val goals: MutableLiveData<State<List<Goal>>> = MutableLiveData()


    internal fun getGoalsHandler() {
        viewModelScope.launch(Dispatchers.IO) {
            emitGoalsState(State.Loading(true))

            getAccess()
                .onSuccess {
                    getGoals(it.accessToken).collect { result ->
                        result
                            .onSuccess { data ->
                                emitGoalsState(State.Success(data))
                                emitGoalsState(State.Loading(false))
                            }
                            .onFailure {
                                emitGoalsState(State.Loading(false))
                                emitGoalsState(State.Failure(getApplication<App>().getString(R.string.general_error)))
                            }
                    }

                }
                .onFailure {
                    emitGoalsState(State.Loading(false))
                    emitGoalsState(State.Failure(it))
                }
        }
    }


    private suspend fun emitGoalsState(state: State<List<Goal>>) {
        withContext(Dispatchers.Main) {
            goals.value = state
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