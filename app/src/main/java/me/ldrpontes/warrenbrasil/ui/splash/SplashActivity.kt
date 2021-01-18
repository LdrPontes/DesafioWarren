package me.ldrpontes.warrenbrasil.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.ui.goals.GoalActivity
import me.ldrpontes.warrenbrasil.ui.login.LoginActivity
import me.ldrpontes.warrenbrasil.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startAccessObserver()
    }


    private fun startAccessObserver() {
        splashViewModel.accessState.observe(this, {
            if (it) {
                startActivity(Intent(this, GoalActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })
    }
}