package ai.retail.myapp.domain.repositories.impl

import ai.retail.myapp.data.DataManager
import ai.retail.myapp.domain.auth.AuthResponse
import ai.retail.myapp.domain.auth.LoginUserPassword
import ai.retail.myapp.domain.auth.RequestForOTP
import ai.retail.myapp.domain.repositories.NumberVerificationRepo
import ai.retail.myapp.network.ApiConstants.SOMETHING_WENT_WRONG
import ai.retail.myapp.network.interfaces.CustomAuthApiService
import ai.retail.myapp.utils.CommonTasks.Companion.getErrorResponseBody
import ai.retail.myapp.utils.Resource
import ai.retail.myapp.utils.Status
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class NumberVerificationRepositoryImpl constructor(
    var mDataManager: DataManager,
    var auth: FirebaseAuth, var customAuthApiService: CustomAuthApiService
): NumberVerificationRepo {

    private val CREDENTIAL_NOT_FOUND = "Credential not found"
    private val VERIFICATION_FAILED = "Verification failed"
    private val TIMEOUT_DURATION = 60

    private var mCode: String? = null
    private var mResendingToken: ForceResendingToken? = null
    private val mLivePhoneAuth = MutableLiveData<Resource<PhoneAuthCredential>>()
    private val mVerifyCode = MutableLiveData<Resource<FirebaseUser>>()
    private var sendOTP: MutableLiveData<Resource<AuthResponse>>? = null
    private var loginWithPassword: MutableLiveData<Resource<AuthResponse>>? = null

    private val mCallbacks: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(code: String, forceResendingToken: ForceResendingToken) {
                mCode = code
                mResendingToken = forceResendingToken
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                mLivePhoneAuth.postValue(Resource.success(phoneAuthCredential))
            }

            override fun onVerificationFailed(e: FirebaseException) {
                e.printStackTrace()
                mLivePhoneAuth.postValue(Resource.error(VERIFICATION_FAILED, null))
            }
        }

    override fun sendVerificationCode(number: String, activity: Activity) {
        if (number != null) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,  // Phone number to verify
                TIMEOUT_DURATION.toLong(),  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                activity,  // Activity (for callback binding)
                mCallbacks
            )
        }
    }

    override fun resendVerificationCode(number: String, activity: Activity) {
        if (number != null) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,  // Phone number to verify
                TIMEOUT_DURATION.toLong(),  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                activity,  // Activity (for callback binding)
                mCallbacks,
                mResendingToken
            )
        }
    }

    @Throws(IllegalArgumentException::class)
    override fun verifyCode(code: String?) {
        val credential = PhoneAuthProvider.getCredential(mCode!!, code!!)
        mLivePhoneAuth.value = Resource.success(credential)
    }

    override fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential?, activity: Activity) {
        if (credential == null) mVerifyCode.postValue(Resource.error(CREDENTIAL_NOT_FOUND, null))
        mVerifyCode.postValue(Resource<FirebaseUser>(Status.LOADING, null, null))
        if (credential != null) {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(activity,
                    OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = task.result!!.user
                            mVerifyCode.setValue(Resource.success(user))
                        } else {
                            mVerifyCode.setValue(
                                Resource.error(task.exception!!.localizedMessage, null)
                            )
                        }
                    })
        }
    }

    override fun getLiveCodeVerification(): MutableLiveData<Resource<FirebaseUser>> {
        return mVerifyCode
    }

    override fun getLiveAuthCred(): MutableLiveData<Resource<PhoneAuthCredential>> {
        return mLivePhoneAuth
    }

    override fun signInWithCustomToken(customToken: String?, activity: Activity) {
        mVerifyCode.postValue(Resource<FirebaseUser>(Status.LOADING, null, null))
        if (customToken != null) {
            auth.signInWithCustomToken(customToken)
                .addOnCompleteListener(activity,
                    OnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = task.result!!.user
                            //                        Log.d(TAG, "signInWithCustomToken: \nsuccess"+task.getResult().getUser().getPhoneNumber()+
                            //                                " :\n UID "+task.getResult().getUser().getUid());
                            mVerifyCode.setValue(Resource.success(user))
                        } else {
                            // If sign in fails, display a message to the user.
                            //                        Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            mVerifyCode.setValue(
                                Resource.error(task.exception!!.localizedMessage, null)
                            )
                        }
                    })
        }
    }


    override fun sendCustomToken(reporter: String?): LiveData<Resource<AuthResponse>> {
        sendOTP = MutableLiveData<Resource<AuthResponse>>()
        sendOTP!!.setValue(Resource.loading(null))
        val requestForOTP = RequestForOTP(reporter, mDataManager.getOTPSecret())
        customAuthApiService.sendOTP(requestForOTP).enqueue(object : Callback<AuthResponse?> {
            override fun onResponse(call: Call<AuthResponse?>, response: Response<AuthResponse?>) {
                if (response.isSuccessful()) {
                    sendOTP!!.setValue(Resource.success(response.body()))
                } else {
                    try {
                        sendOTP!!.setValue(
                            Resource.error(
                                SOMETHING_WENT_WRONG, getErrorResponseBody(
                                    response.errorBody()!!.string()
                                )
                            )
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse?>, t: Throwable) {
                val temp = AuthResponse()
                temp.code = 500
                temp.errorType = "Internal Error"
                temp.message = t.message
                sendOTP!!.setValue(Resource.error(t.message!!, temp))
            }
        })
        return sendOTP as MutableLiveData<Resource<AuthResponse>>
    }


    override fun signInWithCustomPassword(loginUserPassword: LoginUserPassword): LiveData<Resource<AuthResponse>> {
        loginWithPassword = MutableLiveData()
        loginWithPassword!!.value = Resource.loading(null)
        customAuthApiService.login(loginUserPassword)?.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    loginWithPassword!!.value = Resource.success(response.body())
                } else {
                    try {
                        loginWithPassword!!.setValue(
                            Resource.error(
                                SOMETHING_WENT_WRONG, getErrorResponseBody(
                                    response.errorBody()!!.string()
                                )
                            )
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse?>, t: Throwable) {
                val temp = AuthResponse()

                temp.code = 500
                temp.errorType = "Internal Error"
                temp.message = t.message
                loginWithPassword!!.setValue(Resource.error(t.message!!, temp))
            }
        })
        return loginWithPassword as MutableLiveData<Resource<AuthResponse>>
    }

}