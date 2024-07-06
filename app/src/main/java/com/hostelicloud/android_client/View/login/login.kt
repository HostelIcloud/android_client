package com.hostelicloud.android_client.View.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hostelicloud.android_client.R
import com.hostelicloud.android_client.Repository.login.LoginRepository
import com.hostelicloud.android_client.databinding.ActivityLoginBinding
import com.hostelicloud.android_client.viewmodel.login.LoginViewModel
import com.hostelicloud.android_client.viewmodel.login.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )

        val repository = LoginRepository()
        val viewModelFactory = LoginViewModelFactory(repository)
        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
        binding.activity = this  // Provide the current activity instance

        loginViewModel.isOtpSent.observe(this, Observer { isSent ->
            if (isSent) {
                val intent = Intent(this, OtpActivity::class.java)
                startActivity(intent)
            } else {
                // Handle error case if needed
            }
        })
    }
}
