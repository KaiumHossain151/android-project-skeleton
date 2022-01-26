package ai.retail.myapp.data

interface PreferenceHelper {

    companion object {
        const val MODULE_NAME = "SharedPreference"
        const val VERSION = 1
    }

    fun saveAccessToken(token: String?)


    fun saveOtpSecret(token: String?)

    fun getAccessToken(): String?

    fun getOTPSecret(): String?

}