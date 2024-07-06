package com.hostelicloud.android_client.Model.login


class loginmodel {
    fun sendOtp(phoneNumber: String, callback: (Boolean) -> Unit) {
        // Simulate sending OTP
        callback(true)
    }

    fun verifyOtp(phoneNumber: String, otp: String, callback: (Boolean) -> Unit) {
        // Simulate verifying OTP
        callback(true)
    }
}
