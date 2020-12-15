package me.ldrpontes.warrenbrasil.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    val binding : ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}

