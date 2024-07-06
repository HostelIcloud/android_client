package com.hostelicloud.android_client.View.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.hostelicloud.android_client.R
import com.hostelicloud.android_client.Repository.login.LoginRepository
import com.hostelicloud.android_client.View.home.Homeactivity
import com.hostelicloud.android_client.viewmodel.login.LoginViewModelFactory

import com.hostelicloud.android_client.databinding.ActivityOtpBinding
import com.hostelicloud.android_client.utils.login.Loginpreference
import com.hostelicloud.android_client.viewmodel.login.LoginViewModel


class OtpActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityOtpBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_otp
        )
        val repository = LoginRepository()
        val viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.isOtpVerified.observe(this, Observer { isVerified ->
            if (isVerified) {
//                 Save login state in SharedPreferences
                val preferencesManager = Loginpreference(this)
                preferencesManager.saveLoginState(true)
                val intent = Intent(this, Homeactivity::class.java)
                startActivity(intent)
            } else {
                // Show error
            }
        })
    }
}
