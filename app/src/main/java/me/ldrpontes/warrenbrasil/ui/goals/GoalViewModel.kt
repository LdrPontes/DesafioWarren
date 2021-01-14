package me.ldrpontes.warrenbrasil.ui.goals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.ldrpontes.domain.entities.DataResult
import me.ldrpontes.domain.usecase.GetAccessUseCase
import me.ldrpontes.domain.usecase.GetGoalsParams
import me.ldrpontes.domain.usecase.GetGoalsUseCase

class GoalViewModel(private val getAccessUseCase: GetAccessUseCase, private val getGoalsUseCase: GetGoalsUseCase, application: Application) : AndroidViewModel(application) {

}