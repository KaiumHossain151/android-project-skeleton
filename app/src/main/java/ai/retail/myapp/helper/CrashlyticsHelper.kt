package ai.retail.myapp.helper

import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsHelper constructor (private val mFirebaseCrashlytics: FirebaseCrashlytics) {
    fun setUserIdentifier(id: String?) {
        mFirebaseCrashlytics.setUserId(id!!)
    }

    fun setUserEmail(email: String?) {}
    fun setUserName(name: String?) {
//        mFirebaseCrashlytics.setuserNmae(name);
    }

    fun setLogExceptoin(e: Exception?) {
        mFirebaseCrashlytics.recordException(e!!)
    }

    fun setLog(log: String?) {
        mFirebaseCrashlytics.log(log!!)
    }

    fun setCustomCrash(key: String?, `val`: String?) {
        mFirebaseCrashlytics.setCustomKey(key!!, `val`!!)
    }

    init {
        mFirebaseCrashlytics.setCrashlyticsCollectionEnabled(true)
    }
}