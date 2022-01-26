package ai.retail.myapp.data.impl

import ai.retail.myapp.data.PreferenceHelper
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(encryptedSharedPreferences: EncryptedSharedPreferences?) :
    PreferenceHelper {


    private val KEY_ACCESS_TOKEN = "key_token"
    private val KEY_OTP_SECRET = "KEY_OTP_SECRET"


    private val mSPE: SharedPreferences.Editor? = encryptedSharedPreferences?.edit()
    private val mSP: SharedPreferences? = encryptedSharedPreferences

    override fun saveAccessToken(token: String?) {
        if (mSPE != null) {
            mSPE.putString(KEY_ACCESS_TOKEN, "")
            mSPE.apply()
        }
    }

    override fun saveOtpSecret(token: String?) {
        if (mSPE != null) {
            mSPE.putString(KEY_OTP_SECRET,
                "y6B8DaGdJfNjQmSqVsXu2x4z6C9EbHeKgNkRnTqWtYv2y5A7DaFcHfMhPkSpUrWuZaFcHfMhPkSpUrXuZw3z6B8DbGdJfNjQmTqVsXv2x4z7C9EcHeKgPkRnTrWtYv3y5AYw3y5A8DaFcJfMhPmSpUsXuZw4z6B8EbGdJgNjQnTqVsYv2x4A7C9EcHeKhPkRnUruZw4z6B8EbGdKgNjQnTqVsYv2x4A7C9FcHeKhPkRnUrWtYw3y5B8DaFdJfMhQmSpU"
            )
            mSPE.apply()
        }
    }

    override fun getAccessToken(): String? {
        if (mSP != null) {
            return mSP.getString(KEY_ACCESS_TOKEN, "")
        }else{
            return ""
        }
    }

    override fun getOTPSecret(): String? {
        if (mSP != null) {
            return mSP.getString(KEY_OTP_SECRET, "")
        }else{
            return ""
        }
    }
}