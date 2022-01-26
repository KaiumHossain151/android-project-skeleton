package ai.retail.myapp.helper

import ai.retail.myapp.activities.CodeVerificationActivity
import ai.retail.myapp.activities.HomeActivity
import ai.retail.myapp.activities.SignInActivity
import android.content.Context
import android.content.Intent

class Navigator {
    companion object{
        @JvmStatic
        fun switchToSignIn(context: Context) {
            val intent = Intent(context, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }

    @JvmStatic
    fun switchToVerification(context: Context, phoneNumber: String?) {
        val intent = Intent(context, CodeVerificationActivity::class.java)
        intent.putExtra(CodeVerificationActivity.KEY_TAG_PHONE_NUMBER, phoneNumber)
        context.startActivity(intent)
    }

    @JvmStatic
    fun switchToHome(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    }
}