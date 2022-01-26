package ai.retail.myapp.viewmodels

import ai.retail.myapp.domain.auth.AuthResponse
import ai.retail.myapp.domain.auth.LoginUserPassword
import ai.retail.myapp.domain.repositories.NumberVerificationRepo
import ai.retail.myapp.utils.Resource
import ai.retail.myapp.viewmodels.datamodels.CodeVerificationModel
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CodeVerificationViewModel @Inject constructor(val mNumberVerificationRepo: NumberVerificationRepo)  : ViewModel(){
    val mCodeVerificationModel : CodeVerificationModel = CodeVerificationModel();


    @Throws(IllegalArgumentException::class)
    fun verifyCode() {
        mNumberVerificationRepo.verifyCode(mCodeVerificationModel.getCode())
    }

    fun sendVerificationCode(activity: Activity) {
        mNumberVerificationRepo.sendVerificationCode(mCodeVerificationModel.phoneNumber,activity)
    }

    fun resendVerificationCode(activity: Activity) {
        mCodeVerificationModel.setResend_count()
        mNumberVerificationRepo.resendVerificationCode(mCodeVerificationModel.phoneNumber,activity)
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential?,activity: Activity) {
        mNumberVerificationRepo.signInWithPhoneAuthCredential(credential,activity)
    }

    fun getAuthCredObserver(): MutableLiveData<Resource<PhoneAuthCredential>> {
        return mNumberVerificationRepo.getLiveAuthCred()
    }

    fun getCodeVerificationObserver(): MutableLiveData<Resource<FirebaseUser>> {
        return mNumberVerificationRepo.getLiveCodeVerification()
    }

    fun signInWithCustomToken(token: String?,activity: Activity) {
        mNumberVerificationRepo.signInWithCustomToken(token,activity)
    }

    fun sendCustomToken(reporter: String): LiveData<Resource<AuthResponse>> {
        return mNumberVerificationRepo.sendCustomToken(reporter)
    }

    fun signInWithCustomPassword(): LiveData<Resource<AuthResponse>> {
        val loginUserPassword = LoginUserPassword(mCodeVerificationModel.phoneNumber, mCodeVerificationModel.password)
        return mNumberVerificationRepo.signInWithCustomPassword(loginUserPassword)
    }


}