package ai.retail.myapp.helper

import android.app.Activity

interface ImagePickerWrapper {

    companion object{
        var REQUEST_CODE_CAPTURE_POSM = 1115
        var REQUEST_CODE_CAPTURE_OUTLET_IMAGE = 1116
        var REQUEST_CODE_CAPTURE_QR_IMAGE = 1118
        var REQUEST_CODE_FEEDBACK_IMAGE = 1119
        var REQUEST_CODE_VISITING_CARD_IMAGE = 1120
    }



    /**
     * Captures image
     * @return String file path
     * @param filePrefix as string
     * @param requestCode as integer
     */
    fun captureFullSizeImage(filePrefix: String, requestCode: Int, activity : Activity): String
}