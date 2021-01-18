package me.ldrpontes.warrenbrasil.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import me.ldrpontes.domain.entities.Access
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.databinding.ActivityLoginBinding
import me.ldrpontes.warrenbrasil.ui.goals.GoalActivity
import me.ldrpontes.warrenbrasil.utils.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }
    private val loginViewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding.model = loginViewModel

        startLoginObserver()
    }


    fun onButtonLoginClick(view: View) {
        resetErrorViewsState()

        if (!fieldsErrorHandler()) {
            loginViewModel.doLogin()
        }
    }


    private fun resetErrorViewsState() {
        ln_error.visibility = View.GONE
        tl_email.error = null
        tl_password.error = null
    }


    private fun fieldsErrorHandler(): Boolean {
        if (loginViewModel.email.isEmpty() || loginViewModel.email.isBlank()) {
            tl_email.error = getString(R.string.empty_email_error)
            return true
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(loginViewModel.email).matches()) {
            tl_email.error = getString(R.string.wrong_email)
            return true
        }

        if (loginViewModel.password.isEmpty() || loginViewModel.password.isBlank()) {
            tl_password.error = getString(R.string.empty_password_error)
            return true
        }

        return false
    }


    private fun startLoginObserver() {
        loginViewModel.loginState.observe(this, {
            loginObserverHandler(it)
        })
    }


    private fun loginObserverHandler(state: State<Access>) {

        when (state) {
            is State.Success -> {
                startActivity(Intent(this, GoalActivity::class.java))
            }
            is State.Failure -> {
                ln_error.visibility = View.VISIBLE
                tv_error_login.text = state.message
            }
            is State.Loading -> {
                progress_login.isIndeterminate = state.loading
                progress_login.visibility = if (state.loading) View.VISIBLE else View.GONE
                btn_login.text = if (state.loading) "" else getString(R.string.label_sign_in)
            }
        }
    }


}

