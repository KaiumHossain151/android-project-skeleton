package ai.retail.myapp.dbhelper.dao

import ai.retail.myapp.dbhelper.DbConstants
import ai.retail.myapp.dbhelper.entities.PostEntity
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Query("SELECT * FROM " + DbConstants.ACQUISITION_TABLE)
    fun getPosts(): LiveData<List<PostEntity?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePosts(postEntity: PostEntity?)

    @Query("SELECT * FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE wallet_id = :wallet_id AND isUploaded = 0")
    fun getPosts(wallet_id: String?): LiveData<List<PostEntity?>?>?


    @Query("SELECT COUNT(wallet_id) FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE isUploaded = 0")
    fun getNumberOfPost(): LiveData<Int?>?

    @Query("SELECT * FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE isUploaded = 0")
    fun getSavedAcquisitions(): LiveData<List<PostEntity?>?>?

    @Query("SELECT * FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE isUploaded = 0 AND callType LIKE :callType ")
    fun getSavedDataAccordingToCallType(callType: String?): LiveData<List<PostEntity?>?>?

    @Query("DELETE FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE wallet_id LIKE :wallet_id")
    fun deleteAcquisition(wallet_id: String?)

    @Query("SELECT date FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE isUploaded = 1 GROUP BY date ORDER BY date DESC")
    fun getUploadedAcquisitionGroup(): LiveData<List<String?>?>?

    @Query("SELECT * FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE date LIKE :date AND isUploaded = 1")
    fun getDetailsUploadedAcquisition(date: String?): LiveData<List<PostEntity?>?>?

    @Delete
    fun deleteAcquisition(acquisitionEntity: PostEntity?)

    @Query("DELETE FROM " + DbConstants.ACQUISITION_TABLE)
    fun deleteAllAcquisition()

    @Update
    fun updateAcquisitionInfo(outletEntity: PostEntity?)

    @Query("DELETE FROM " + DbConstants.ACQUISITION_TABLE.toString() + " WHERE date < :previousDate")
    fun deleteAcquisitionPreviousHistory(previousDate: String?)
}