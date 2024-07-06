package com.hostelicloud.android_client.viewmodel.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.hostelicloud.android_client.Repository.login.LoginRepository


class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    val phoneNumber = MutableLiveData<String>()
    val otp = MutableLiveData<String>()
    val isOtpSent = MutableLiveData<Boolean>()
    val isOtpVerified = MutableLiveData<Boolean>()
    private var verificationId: String? = null

    fun sendOtp(activity: Activity?) {
        activity ?: return  // Return early if activity is null
        val phone = phoneNumber.value ?: return
        repository.sendOtp(phone, object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                isOtpSent.value = true
            }

            override fun onVerificationFailed(e: FirebaseException) {
                isOtpSent.value = false
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                this@LoginViewModel.verificationId = verificationId
                isOtpSent.value = true
            }
        }, activity)
    }


    fun verifyOtp() {
        val verificationId = verificationId ?: return
        val otpValue = otp.value ?: return
        repository.verifyOtp(verificationId, otpValue) {
            isOtpVerified.value = it
        }
    }
}
