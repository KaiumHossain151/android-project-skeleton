package ai.retail.myapp.domain.repositories

import ai.retail.myapp.domain.auth.AuthResponse
import ai.retail.myapp.domain.auth.LoginUserPassword
import ai.retail.myapp.utils.Resource
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential

interface NumberVerificationRepo {
    fun sendVerificationCode(number: String, activity: Activity)

    fun resendVerificationCode(number: String, activity: Activity)

    fun verifyCode(code: String?)

    fun signInWithPhoneAuthCredential(phoneAuthCredential: PhoneAuthCredential?,activity: Activity)

    fun getLiveCodeVerification(): MutableLiveData<Resource<FirebaseUser>>

    fun getLiveAuthCred(): MutableLiveData<Resource<PhoneAuthCredential>>

    fun signInWithCustomToken(customToken: String?,activity: Activity)

    fun sendCustomToken(reporter: String?): LiveData<Resource<AuthResponse>>

    fun signInWithCustomPassword(loginUserPassword: LoginUserPassword): LiveData<Resource<AuthResponse>>
}