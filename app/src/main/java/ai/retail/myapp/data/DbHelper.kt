package ai.retail.myapp.data

import ai.retail.myapp.dbhelper.entities.PostEntity
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

interface DbHelper {
    @WorkerThread
    fun saveAcquisition(acquisitionEntity: PostEntity?)

    fun getAcquisition(wallet_id: String?): LiveData<List<PostEntity?>?>?

    fun getSavedAcquisitionInfoList(): LiveData<List<PostEntity?>?>?

    fun deleteAllAcquisition()

    fun updateAcquisition(acquisitionEntity: PostEntity?)

    fun deleteAcquisition(wallet_id: String?)

    fun deleteAcquisition(acquisitionEntity: PostEntity?)
}