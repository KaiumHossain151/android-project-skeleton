package ai.retail.myapp.firebase.storage.impl

import ai.retail.myapp.firebase.storage.FirebaseStorageHelper
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_SELFIE_ATTENDANCE
import ai.retail.myapp.utils.*
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayInputStream
import java.io.File
import java.lang.Exception
import java.util.HashMap

class FirebaseStorageHelperImpl constructor(private val storageReference: StorageReference) :
    FirebaseStorageHelper {
    private val imageNotFound = "Image not found"
    private val imageUploadFailed = "Image upload failed"

    private lateinit var mLiveAllCallsImages: MutableLiveData<Resource<Map<String, String>>>

    private val mLiveAttendanceImage: MutableLiveData<Resource<String>> =
        MutableLiveData<Resource<String>>()


    override fun uploadSelfAttendanceReference(file: File): LiveData<Resource<String>> {
        mLiveAttendanceImage.value = Resource.loading(null)
        val imageStorageRef: StorageReference = storageReference.child(
            CommonTasks.add_folder_sign(CHILD_SELFIE_ATTENDANCE) + file.name
        )
        try {
            val compressedByteArray = ByteArrayInputStream(ImageUtils.compressImage(file.path))
            val uploadTask: UploadTask = imageStorageRef.putStream(compressedByteArray)
            uploadTask.addOnSuccessListener {
                imageStorageRef.downloadUrl.addOnSuccessListener { uri ->
                    file.delete()
                    mLiveAttendanceImage.setValue(Resource.success(uri.toString()))
                }.addOnFailureListener {
                    mLiveAttendanceImage.setValue(
                        Resource.error(imageUploadFailed, null)
                    )
                }
            }.addOnFailureListener {
                mLiveAttendanceImage.setValue(
                    Resource.error(imageUploadFailed, null)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mLiveAttendanceImage.setValue(Resource.error(imageNotFound, null))
        }
        return mLiveAttendanceImage
    }

    override fun uploadAllCallsImages(images: Map<String, String>): LiveData<Resource<Map<String, String>>> {
        mLiveAllCallsImages = MutableLiveData<Resource<Map<String, String>>>()
        uploadAllCallsImageRec(images as HashMap<String, String>, HashMap(), HashMap())
        return mLiveAllCallsImages
    }


    private fun uploadAllCallsImageRec(
        images: HashMap<String, String>,
        uploadedFilePath: HashMap<String, String>,
        deletedFilePath: HashMap<String, String?>
    ) {
        if (mapUtils.isEmpty(images)) {
            mLiveAllCallsImages.value = Resource.success(uploadedFilePath)
            FileUtils.deleteAllImages(deletedFilePath)
            return
        }
        val key: String = CommonTasks.getCurrentKey(images)
        val value = images[CommonTasks.getCurrentKey(images)]
        val imageFile = File(value!!)
        val uri = Uri.fromFile(imageFile)
        if (FileUtils.isUrl(value.toString())) {
            uploadedFilePath[key] = FirebaseUtils.getImagePath(uri)
            images.remove(key)
            uploadAllCallsImageRec(images, uploadedFilePath, deletedFilePath)
            return
        }
        val imageStorageRef: StorageReference =
            storageReference.child(CommonTasks.add_folder_sign(key) + imageFile.name)
        mLiveAllCallsImages.value = Resource.loading(uploadedFilePath)
        try {
            val compressedByteArray = ByteArrayInputStream(ImageUtils.compressImage(imageFile.path))
            val uploadTask: UploadTask = imageStorageRef.putStream(compressedByteArray)
            uploadTask.addOnSuccessListener {
                imageStorageRef.downloadUrl.addOnSuccessListener { uri1 ->
                    uploadedFilePath[key] = FirebaseUtils.getImagePath(uri1)
                    images.remove(key)
                    deletedFilePath[key] = value
                    uploadAllCallsImageRec(images, uploadedFilePath, deletedFilePath)
                }.addOnFailureListener {
                    mLiveAllCallsImages.setValue(
                        Resource.error(imageUploadFailed, null)
                    )
                }
            }.addOnFailureListener {
                mLiveAllCallsImages.setValue(
                    Resource.error(imageUploadFailed, null)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mLiveAllCallsImages.setValue(Resource.error(imageNotFound, null))
        }
    }
}