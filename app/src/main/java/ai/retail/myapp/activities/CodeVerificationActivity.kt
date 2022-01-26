package ai.retail.myapp.activities

import ai.retail.myapp.R
import ai.retail.myapp.databinding.ActivityCodeVerificationBinding
import ai.retail.myapp.helper.Navigator
import ai.retail.myapp.utils.CommonTasks
import ai.retail.myapp.utils.Constants
import ai.retail.myapp.utils.Resource
import ai.retail.myapp.utils.Status
import ai.retail.myapp.viewmodels.CodeVerificationViewModel
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import java.util.*

class CodeVerificationActivity : BaseActivity(), View.OnClickListener{
    private lateinit var mBinding: ActivityCodeVerificationBinding
    private val mViewModel: CodeVerificationViewModel by viewModels()

    companion object{
        const val KEY_TAG_PHONE_NUMBER = "key_phone_number"
    }
    private var progressStatus = 1

    private val mAuthCredObserver: Observer<Resource<PhoneAuthCredential?>> = Observer<Resource<PhoneAuthCredential?>> { credential: Resource<PhoneAuthCredential?> ->
        when (credential.status) {
            Status.ERROR -> {
                mViewModel.mCodeVerificationModel.setIs_password_login_button_active(Constants.TRUE)
                CommonTasks.showToast(this@CodeVerificationActivity, credential.message)
            }
            Status.SUCCESS -> {
                mViewModel.signInWithPhoneAuthCredential(credential.data,this)
                assert(credential.data != null)
                val smsCode: String? = credential.data?.getSmsCode()
                mBinding.tieVerificationCode.setText(smsCode)
                if (!TextUtils.isEmpty(smsCode)) {
                    assert(smsCode != null)
                    mBinding.tieVerificationCode.setSelection(smsCode!!.length)
                }
            }
        }
    }

    private val mCodeVerificationObserver = Observer<Resource<FirebaseUser>> { (status) ->
        when (status) {
            Status.ERROR -> {
                cancelProgressDialog()
                CommonTasks.showToast(this, getString(R.string.waring_invalid_otp))
            }
            Status.SUCCESS -> {
                cancelProgressDialog()
                Navigator.switchToHome(this)
            }
            Status.LOADING -> showProgressDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_code_verification)
        initVariables()
        extractData()
        bindVariables()
//        setCodeResendText();
        //        setCodeResendText();
        initListeners()
        setStatusBar()
        bindObservers()
        sendVerificationCode()
        setCodeResendTestInit()
        setCountDownTimer()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun setStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.parseColor("#ffffff")
    }


    private fun setCodeResendText() {
        val sb = SpannableString(resources.getString(R.string.text_resend_code))
        val fcs = ForegroundColorSpan(resources.getColor(R.color.colorPrimary, null))
        val bss = StyleSpan(Typeface.BOLD)
        sb.setSpan(fcs, 13, 23, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        sb.setSpan(bss, 13, 23, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        //        String html = "<pre>ওটিপি পাননি? <span style=\"color: #e3106e;\"><strong>আবার পাঠান</strong></span></pre>";
        mBinding.tvResendCode.isEnabled = Constants.TRUE
        mBinding.tvResendCode.text = sb
    }

    private fun setCodeResendTestInit() {
        val sb = SpannableString(resources.getString(R.string.text_resend_code))
        val fcs = ForegroundColorSpan(resources.getColor(R.color.btn_disabled, null))
        val bss = StyleSpan(Typeface.NORMAL)
        sb.setSpan(fcs, 13, 23, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        sb.setSpan(bss, 13, 23, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        //        String html = "<pre>ওটিপি পাননি? <span style=\"color: #e3106e;\"><strong>আবার পাঠান</strong></span></pre>";
        mBinding.tvResendCode.isEnabled = Constants.FALSE
        mBinding.tvResendCode.text = sb
    }

    private fun bindObservers() {
        mViewModel.getAuthCredObserver().observe(this, mAuthCredObserver)
        mViewModel.getCodeVerificationObserver().observe(this, mCodeVerificationObserver)
    }

    override fun initListeners() {
        mBinding.btnVerifyCode.setOnClickListener(this)
        mBinding.tvResendCode.setOnClickListener(this)
        mBinding.tvLoginWithPassword.setOnClickListener(this)
        mBinding.btnVerifyPassword.setOnClickListener(this)
    }


    private fun bindVariables() {
        mBinding.codeModel = mViewModel.mCodeVerificationModel
//        String message = String.format(getString(R.string.text_verify_phone_number_subtitle), mViewModel.getCodeVerificationModel().getPhoneNumber());
//        mBinding.tvVerifyNumberSubtitle.setText(message);
    }

    private fun extractData() {
        mViewModel.mCodeVerificationModel.setPhoneNumber(Objects.requireNonNull(intent.extras)!!.getString(KEY_TAG_PHONE_NUMBER, Constants.EMPTY_STRING))
    }

    override fun initVariables() {

    }

    override fun onClick(v: View) {
        val id: Int = v.getId()
        if (id == R.id.btn_verify_code) {
            verifyCode()
        } else if (id == R.id.tv_resend_code) {
            setCountDownTimer()
            resendCode()
            setCodeResendTestInit()
        } else if (id == R.id.btn_verify_password) {
//            signInWithCustomPassword()
        } else if (id == R.id.tv_login_with_password) {
//            mViewModel.mCodeVerificationModel.setIs_password_mode_active(TRUE)
//            sendCustomToken()
        }
    }


//    private fun showResendDialog(isPositiveButtonActive: Boolean, isNegativeButtonAcive: Boolean, title: String, message: String){
//        showTerminationAlert(isPositiveButtonActive, isNegativeButtonAcive, getString(R.string.text_ok), getString(R.string.text_cancel), title, message, object : OnTerminationAcceptedListener {
//            override fun onComplete() {
//                sendCustomToken()
//            }
//        })
//    }

//    private fun sendCustomToken() {
//        mViewModel.sendCustomToken(mViewModel.mCodeVerificationModel.getPhoneNumber()).observe(this, { (status, data) ->
//            when (status) {
//                Status.ERROR -> {
//                    cancelProgressDialog()
//                    if (data?.code === 404) {
//                        data.errorType?.let { data.message?.let { it1 -> showResendDialog(FALSE, TRUE, it, it1) } }
//                    } else {
//                        data?.errorType?.let { data?.message?.let { it1 -> showResendDialog(TRUE, TRUE, it, it1) } }
//                    }
//                }
//                Status.SUCCESS -> {
//                    cancelProgressDialog()
//                    setCountDownTimer()
//                    CommonTasks.showToast(this, data?.message)
//                }
//                Status.LOADING -> showProgressDialog()
//            }
//        })
//    }

//    private fun signInWithCustomPassword() {
//        mViewModel.signInWithCustomPassword().observe(this, { (status, data) ->
//            when (status) {
//                Status.ERROR -> {
//                    cancelProgressDialog()
//                    if (data?.code === 401) {
//                        CommonTasks.showToast(this, data.message)
//                    } else if (data?.code === 404) {
//                        data.errorType?.let { data.message?.let { it1 -> showResendDialog(FALSE, TRUE, it, it1) } }
//                    } else {
//                        data?.errorType?.let { data?.message?.let { it1 -> showResendDialog(TRUE, TRUE, it, it1) } }
//                    }
//                }
//                Status.SUCCESS ->
//                    mViewModel.signInWithCustomToken(data?.token,this)
//                Status.LOADING -> showProgressDialog()
//            }
//        })
//    }


    private fun setCountDownTimer() {
        progressStatus = 1
        val OTP_SLEEP_DELAY_IN_MILLIS: Long = 60000
        val PASSWORD_SLEEP_DELAY_IN_MILLIS: Long = 600000
        val temp_millis = if (mViewModel.mCodeVerificationModel.isIs_password_mode_active()) PASSWORD_SLEEP_DELAY_IN_MILLIS else OTP_SLEEP_DELAY_IN_MILLIS
        val tick_unit_otp: Long = 600
        val tick_unit_password: Long = 6000
        val temp_tick = if (mViewModel.mCodeVerificationModel.isIs_password_mode_active()) tick_unit_password else tick_unit_otp
        object : CountDownTimer(temp_millis, temp_tick) {
            override fun onTick(millisUntilFinished: Long) {
//                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
//                Log.d("onTick",""+millisUntilFinished);
                progressStatus += 1
                mBinding.codeLaunchProgressBar.progress = progressStatus
            }

            override fun onFinish() {
                setCodeResendText()
                //                if (mViewModel.getCodeVerificationModel().isIs_password_mode_active()){
//                    showResendDialog(TRUE,TRUE,getString(R.string.code_validity_expired),getString(R.string.code_validity_expired_resend_code));
//                }
            }
        }.start()
    }

    private fun resendCode() {
        mViewModel.resendVerificationCode(this)
        //        setCodeResendText();
        CommonTasks.showToast(this, getString(R.string.toast_code_resent))
    }

    private fun sendVerificationCode() {
        mViewModel.sendVerificationCode(this)
    }

    private fun verifyCode() {
        try {
            mViewModel.verifyCode()
        } catch (ex: IllegalArgumentException) {
//            Log.d("Verfication failed",ex.getMessage());
            CommonTasks.showToast(this, getString(R.string.verification_failed))
        }
    }

}