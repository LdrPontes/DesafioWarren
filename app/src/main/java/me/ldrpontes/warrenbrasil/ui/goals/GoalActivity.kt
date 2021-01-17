package me.ldrpontes.warrenbrasil.ui.goals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.ldrpontes.warrenbrasil.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoalActivity : AppCompatActivity() {
    private val goalViewModel: GoalViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)
    }
}