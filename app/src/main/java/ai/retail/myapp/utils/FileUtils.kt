package ai.retail.myapp.utils

import android.app.Activity
import android.os.Environment
import android.text.TextUtils
import java.io.File
import java.io.IOException
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class FileUtils {

    companion object {
        fun deleteFile(imagePath: String?) {
            if (TextUtils.isEmpty(imagePath)) return
            val file = File(imagePath!!)
            file.delete()
        }

        @Throws(IOException::class)
        fun createImageFile(userPhone: String, activity: Activity): File? {
            // Create an image file name
            val timeStamp =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val randomNum = Random().nextInt(9000) + 1000
            val imageFileName = userPhone + "_JPEG_" + timeStamp + "_" + randomNum
            val storageDir =
                activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File.createTempFile(
                imageFileName,  /* prefix */
                Constants.IMAGE_FILE_SUFFIX,  /* suffix */
                storageDir /* directory */
            )
        }

        fun deleteAllCallsFile(filePaths: Map<String?, String?>) {
            for ((_, value) in filePaths) {
                deleteFile(value.toString())
            }
        }

        fun deleteAllCallsFile3(filePaths: Map<String, String>) {
            for ((_, value) in filePaths) {
                deleteFile(value)
                deleteFile(CommonTasks.modifyUrlForRESTAPI(value))
            }
        }

        fun deleteAllFile(filePaths: List<String?>) {
            for (filePath in filePaths) {
                deleteFile(filePath)
            }
        }

        fun deleteAllImages(filePaths: HashMap<String, String?>) {
            for ((_, value) in filePaths) {
                deleteFile(value.toString())
            }
        }


        fun isUrl(filePath: String): Boolean {
            return filePath.startsWith(Constants.URL_PREFIX)
        }

        fun clearDirectory(activity: Activity) {
            try {
                val storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                if (storageDir!!.isDirectory) {
                    val children = storageDir.list()
                    for (i in children.indices) {
                        File(storageDir, children[i]).delete()
                    }
                }
            } catch (npe: NullPointerException) {
            }
        }
    }
}