package ai.retail.myapp.helper

import ai.retail.myapp.R
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class PermissionManager {

    /**
     * Declared [PermissionManager] member variables
     */
    companion object {
        val ALL_PERMISSION_REQUEST_CODE = 100
        val LOCATION_PERMISSION_REQUEST_CODE = 200
        val CAMERA_PERMISSION_REQUEST_CODE = 600
        private val REQUEST_SETTINGS = 444
        val PHONE_STATE_PERMISSION_REQUEST_CODE = 500
        val REQUEST_CALL_HELP_LIEN = 11
        val REQUEST_CALL_CONTACT_PERSON = 12
    }


    /**
     * Checks location permission
     * @param context as [Activity]
     * @return boolean value
     */
    fun hasLocationPermission(context: Activity?): Boolean {
        return hasCoarseLocationPermission(context) && hasFineLocationPermission(context)
    }

    /**
     * Checks coarse location permission
     * @param context as [Activity]
     * @return boolean value
     */
    fun hasCoarseLocationPermission(context: Activity?): Boolean {
        return checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    /**
     * Checks fine location permission
     * @param context as [Activity]
     * @return boolean value
     */
    fun hasFineLocationPermission(context: Activity?): Boolean {
        return checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    /**
     * Checks phone state permission
     * @param context as [Activity]
     * @return boolean value
     */
    fun hasPhoneStatePermission(context: Activity?): Boolean {
        return checkPermission(context, Manifest.permission.READ_PHONE_STATE)
    }

    /**
     * Checks camera permission
     * @param context as [Activity]
     * @return boolean value
     */
    fun hasCameraPermission(context: Activity?): Boolean {
        return checkPermission(context, Manifest.permission.CAMERA)
    }


    fun hasPhoneCallPermission(context: Activity?): Boolean {
        return checkPermission(context, Manifest.permission.CALL_PHONE)
    }

    /**
     *
     * Returns true if the user has previously denied the request,
     * and returns false if a user has denied a permission and selected the Don't ask again option in the permission request dialog,
     * or if a device policy prohibits the permission.
     * @param context as [Activity]
     * @return boolean value
     */
    fun shouldShowCameraPermissionRationale(context: Activity?): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            context!!,
            Manifest.permission.CAMERA
        )
    }

    /**
     *
     * Returns true if the user has previously denied the request,
     * and returns false if a user has denied a permission and selected the Don't ask again option in the permission request dialog,
     * or if a device policy prohibits the permission.
     * @param context as [Activity]
     * @return boolean value
     */
    fun shouldShowPhoneStatePermissionRationale(context: Activity?): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            context!!,
            Manifest.permission.READ_PHONE_STATE
        )
    }

    /**
     *
     * Returns true if the user has previously denied the request,
     * and returns false if a user has denied a permission and selected the Don't ask again option in the permission request dialog,
     * or if a device policy prohibits the permission.
     * @param context as [Activity]
     * @return boolean value
     */
    fun shouldShowLocationPermissionRationale(context: Activity?): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            context!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun shouldShowCallPhonePermissionRationale(context: Activity?): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            context!!,
            Manifest.permission.CALL_PHONE
        )
    }

    /**
     * Checks camera permission and location permission
     * @param activity as instance of [AppCompatActivity]
     * @return boolean value
     */
    fun hasAllInitialPermission(activity: AppCompatActivity?): Boolean {
        return hasCameraPermission(activity) && hasLocationPermission(activity)
    }

    /**
     * Checks permissions
     * @param context as [Context]
     * @param permission as String
     * @return boolean value
     */
    private fun checkPermission(context: Context?, permission: String): Boolean {
        return try {
            !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                context!!, permission
            ) != PackageManager.PERMISSION_GRANTED)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Requests for all initial permissions
     * @param ctx as [Activity]
     */
    fun requestForAllInitialPermission(ctx: Activity?) {
        if (ctx != null) {
            ActivityCompat.requestPermissions(
                ctx, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA
                ),
                ALL_PERMISSION_REQUEST_CODE
            )
        }
    }

    /**
     * Requests for location permission
     * @param ctx as [Activity]
     */
    fun requestForLocationPermission(ctx: Activity?) {
        ActivityCompat.requestPermissions(
            ctx!!,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }


    /**
     * Requests for camera permission
     * @param ctx as [Activity]
     */
    fun requestForCameraPermission(ctx: Activity) {
        requestPermissions(
            ctx as AppCompatActivity,
            CAMERA_PERMISSION_REQUEST_CODE,
            ctx.getString(R.string.text_provide_camera_permission),
            Manifest.permission.CAMERA
        )
    }

    fun requestForCallPhonePermission(ctx: Activity) {
        requestPermissions(
            ctx as AppCompatActivity,
            REQUEST_CALL_CONTACT_PERSON,
            ctx.getString(R.string.text_phone_call_permission),
            Manifest.permission.CALL_PHONE
        )
    }

    /**
     * Requests for phone state permission
     * @param activity as [Activity]
     */
    fun requestForPhoneState(activity: Activity) {
        requestPermissions(
            activity as AppCompatActivity,
            PHONE_STATE_PERMISSION_REQUEST_CODE,
            activity.getString(R.string.text_provide_phone_state_permission),
            Manifest.permission.READ_PHONE_STATE
        )
    }

    /**
     * Request permission set for location
     */
    fun requestPermissions(
        activity: AppCompatActivity?,
        requestCode: Int,
        message: String?,
        vararg permissions: String?
    ) {
        var showSnackBar = true
        for (permission in permissions) {
            showSnackBar = showSnackBar and ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                permission!!
            )
        }
        if (showSnackBar) {
            showSettingsSnackbar(message, activity)
        } else {
            ActivityCompat.requestPermissions(activity!!, permissions, requestCode)
        }
    }

    /**
     * Request for permissions
     * @param activity as [AppCompatActivity]
     * @param requestCode as integer
     * @param message as String
     * @param permission as String
     */
    fun requestPermissions(
        activity: AppCompatActivity?,
        requestCode: Int,
        message: String?,
        permission: String
    ) {
        ActivityCompat.requestPermissions(activity!!, arrayOf(permission), requestCode)
    }

    /**
     * Show snack bar for setting
     * @param message as String
     * @param activity as [Activity]
     */
    fun showSettingsSnackbar(message: String?, activity: Activity?) {
        if (activity != null) {
            val view = activity.findViewById<View>(R.id.content)
            Snackbar.make(view, message!!, Snackbar.LENGTH_LONG)
                .setAction(
                    activity.getString(R.string.settings)
                ) { startPermissionScreen(activity) }
                .setActionTextColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                .show()
        }
    }

    /**
     * Starts permission screen
     * @param activity as [Activity]
     */
    fun startPermissionScreen(activity: Activity) {
        val settingsIntent = Intent()
        settingsIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        settingsIntent.addCategory(Intent.CATEGORY_DEFAULT)
        settingsIntent.data = Uri.parse("package:" + activity.packageName)
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        activity.startActivityForResult(settingsIntent, REQUEST_SETTINGS)
    }
}