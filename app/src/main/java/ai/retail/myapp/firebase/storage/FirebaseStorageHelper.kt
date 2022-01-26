package ai.retail.myapp.firebase.storage

import ai.retail.myapp.utils.Resource
import androidx.lifecycle.LiveData
import java.io.File

interface FirebaseStorageHelper {

    companion object {
        const val FIREBASE_STORAGE_URL : String = "gs://bkashnimontron.appspot.com"
        const val CHILD_SHOP_IMAGE = "shop_image"
        const val CHILD_SHOP_REMARKS_IMAGE_1 = "shop_remarks_image_1"
        const val CHILD_SHOP_REMARKS_IMAGE_2 = "shop_remarks_image_2"
        const val CHILD_KYC_IMAGE = "kyc_image"
        const val CHILD_POSM_IMAGE = "posm_image"
        const val CHILD_QR_IMAGE = "qr_image"
        const val CHILD_FORM_IMAGE = "form_image"
        const val  CHILD_SELFIE_ATTENDANCE = "selfieAttendance"
        const val CHILD_SHOP_IMAGE_COMPETITION_MDO = "competition_outlet_image_mdo"
        const val CHILD_SHOP_IMAGE_SURVEY_MDO = "survey_image_mdo"
        const val CHILD_POSM_IMAGE_COMPETITION_MDO = "competition_posm_image_mdo"
    }


    fun uploadSelfAttendanceReference(file: File): LiveData<Resource<String>>

    fun uploadAllCallsImages(images: Map<String, String>): LiveData<Resource<Map<String, String>>>

}