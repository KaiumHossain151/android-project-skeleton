package ai.retail.myapp.activities

import ai.retail.myapp.R
import ai.retail.myapp.databinding.ActivitySigninBinding
import ai.retail.myapp.helper.Navigator
import ai.retail.myapp.utils.CommonTasks
import ai.retail.myapp.utils.Constants.regxPattern
import ai.retail.myapp.viewmodels.AuthViewModel
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivitySigninBinding
    private val mViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        initVariables()
        initListeners()
        bindVariables()
    }

     override fun initVariables() {

    }

     override fun initListeners() {
        mBinding.btnSignIn.setOnClickListener(this)
    }
     fun bindVariables() {
        mBinding.signinModel = mViewModel.signInModel
    }

    override fun onClick(v: View) {
        val id: Int = v.getId()
        if (id == R.id.btn_sign_in) {
            if (CommonTasks.isOnline(this)) {
                val phoneNumber = getString(R.string.number_prefix) + mViewModel.signInModel.getPhoneNumber()
                if (mViewModel.signInModel.getPhoneNumber().matches(regxPattern.toRegex())) {
                    Navigator.switchToVerification(this, phoneNumber)
                } else {
                    mBinding.etPhoneNumber.error = getString(R.string.text_enter_wrong_outlet_number)
                }
            } else {
                showSnackBar(v, getString(R.string.please_keep_your_internet_connection_truned_on))
            }
        }
    }

}