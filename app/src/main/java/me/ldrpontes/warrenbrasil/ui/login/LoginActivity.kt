package me.ldrpontes.warrenbrasil.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_login.*
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.databinding.ActivityLoginBinding
import me.ldrpontes.warrenbrasil.ui.goals.GoalActivity

class LoginActivity : AppCompatActivity() {

    val binding : ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            startActivity(Intent(this, GoalActivity::class.java))
        }
    }
}

