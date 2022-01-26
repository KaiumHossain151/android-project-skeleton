package ai.retail.myapp.data.impl

import ai.retail.myapp.data.DbHelper
import ai.retail.myapp.dbhelper.database.ProjectDB
import ai.retail.myapp.dbhelper.entities.PostEntity
import androidx.lifecycle.LiveData
import javax.inject.Inject

class DbHelperImpl @Inject constructor(val projectDB: ProjectDB): DbHelper {


    override fun saveAcquisition(acquisitionEntity: PostEntity?) {
        projectDB.getAcquisitionDao()?.savePosts(acquisitionEntity)
    }

    override fun getAcquisition(wallet_id: String?): LiveData<List<PostEntity?>?>? {
        return projectDB.getAcquisitionDao()?.getPosts(wallet_id)
    }

    override fun getSavedAcquisitionInfoList(): LiveData<List<PostEntity?>?>? {
        return projectDB.getAcquisitionDao()?.getSavedAcquisitions()
    }

    override fun deleteAllAcquisition() {
        projectDB.getAcquisitionDao()?.deleteAllAcquisition()
    }

    override fun updateAcquisition(acquisitionEntity: PostEntity?) {
        projectDB.getAcquisitionDao()?.updateAcquisitionInfo(acquisitionEntity)
    }

    override fun deleteAcquisition(wallet_id: String?) {
        projectDB.getAcquisitionDao()?.deleteAcquisition(wallet_id)
    }

    override fun deleteAcquisition(acquisitionEntity: PostEntity?) {
        projectDB.getAcquisitionDao()?.deleteAcquisition(acquisitionEntity)
    }
}