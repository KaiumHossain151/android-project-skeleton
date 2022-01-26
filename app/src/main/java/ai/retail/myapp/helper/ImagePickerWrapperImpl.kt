package ai.retail.myapp.helper

import ai.retail.myapp.utils.Constants
import ai.retail.myapp.utils.FileUtils
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera.CameraInfo
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException

class ImagePickerWrapperImpl : ImagePickerWrapper{

    private val CAMERA_FACING_EXTRA_KEY = "android.intent.extras.CAMERA_FACING"

    override fun captureFullSizeImage(filePrefix: String, requestCode: Int, activity: Activity): String {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(
            CAMERA_FACING_EXTRA_KEY,
            CameraInfo.CAMERA_FACING_BACK
        )
        // Ensure that there's a camera activity to handle the intent
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            // Create the File where the photo should go
            val photoFile: File
            try {
                photoFile = FileUtils.createImageFile(
                    filePrefix,
                    activity
                )!! // Continue only if the File was successfully created
                if (photoFile != null) {
                    val authority: String = activity.getPackageName() + ".fileProvider"
                    val photoURI = FileProvider.getUriForFile(
                        activity,
                        authority,
                        photoFile
                    )
                    grantAppPermission(activity, takePictureIntent, photoURI)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    activity.startActivityForResult(takePictureIntent, requestCode)
                    return photoFile.absolutePath
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return Constants.EMPTY_STRING
    }

    private fun grantAppPermission(context: Context, intent: Intent, fileUri: Uri) {
        val resolvedIntentActivities = context.packageManager
            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolvedIntentInfo in resolvedIntentActivities) {
            val packageName = resolvedIntentInfo.activityInfo.packageName
            context.grantUriPermission(
                packageName, fileUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}