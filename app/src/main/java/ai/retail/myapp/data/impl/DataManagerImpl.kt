package ai.retail.myapp.data.impl

import ai.retail.myapp.data.DataManager
import ai.retail.myapp.data.DbHelper
import ai.retail.myapp.data.PreferenceHelper
import ai.retail.myapp.dbhelper.entities.PostEntity
import ai.retail.myapp.firebase.references.FirebaseReferences
import androidx.lifecycle.LiveData
import javax.inject.Inject

class DataManagerImpl @Inject constructor(val dbHelper: DbHelper, val preferenceHelper: PreferenceHelper, val firebaseReferences: FirebaseReferences) :
    DataManager {


    override fun saveAcquisition(acquisitionEntity: PostEntity?) {
        dbHelper.saveAcquisition(acquisitionEntity)
    }

    override fun getAcquisition(wallet_id: String?): LiveData<List<PostEntity?>?>? {
       return dbHelper.getAcquisition(wallet_id)
    }

    override fun getSavedAcquisitionInfoList(): LiveData<List<PostEntity?>?>? {
        return dbHelper.getSavedAcquisitionInfoList()
    }

    override fun deleteAllAcquisition() {
        dbHelper.deleteAllAcquisition()
    }

    override fun updateAcquisition(acquisitionEntity: PostEntity?) {
        dbHelper.updateAcquisition(acquisitionEntity)
    }

    override fun deleteAcquisition(wallet_id: String?) {
        dbHelper.deleteAcquisition(wallet_id)
    }

    override fun deleteAcquisition(acquisitionEntity: PostEntity?) {
        dbHelper.deleteAcquisition(acquisitionEntity)
    }

    override fun saveAccessToken(token: String?) {
        preferenceHelper.saveAccessToken(token)
    }

    override fun saveOtpSecret(token: String?) {
        preferenceHelper.saveOtpSecret(token)
    }

    override fun getAccessToken(): String? {
        return preferenceHelper.getAccessToken()
    }

    override fun getOTPSecret(): String? {
        return preferenceHelper.getOTPSecret()
    }

}