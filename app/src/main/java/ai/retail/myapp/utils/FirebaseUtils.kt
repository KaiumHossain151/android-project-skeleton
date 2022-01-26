package ai.retail.myapp.utils

import android.net.Uri

class FirebaseUtils {
    companion object{

        @JvmStatic
        fun getImagePath(imageUri: Uri): String {
        return imageUri.toString()
    }
}

}