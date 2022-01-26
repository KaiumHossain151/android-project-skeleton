package ai.retail.myapp.helper

import ai.retail.myapp.utils.Constants
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.android.gms.location.*

class LocationManager(context: Context?) : LiveData<Location?>() {
    private lateinit var mLocationRequest: LocationRequest
    private val MAX_RETRY = 7
    private var mCurrentCount = 0
    private var mBestLocation: Location? = null
    private val mLocationCallback: LocationCallback
    private val mFusedLocationProviderClient: FusedLocationProviderClient
    private fun onConnected() {
        if (hasActiveObservers()) {
            mCurrentCount = 0
            mBestLocation = null
            mLocationRequest = LocationRequest()
            mLocationRequest.setInterval(LOCATION_INTERVAL)
            mLocationRequest.setFastestInterval(FASTEST_LOCATION_INTERVAL)
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        }
    }

    override fun onActive() {
        // Wait for the GoogleApiClient to be connected
        connectLocationRequest()
    }

    /**
     * Called when we need to disconnect location request
     */
    override fun onInactive() {
        disconnectLocationRequest()
    }

    /**
     * Sets connection with google api client
     */
    @SuppressLint("MissingPermission")
    fun connectLocationRequest() {
        Log.d(TAG, "connectLocationRequest: ")
        onConnected()
        mFusedLocationProviderClient.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            null
        )
    }

    /**
     * Removes connection with google api client
     */
    fun disconnectLocationRequest() {
        Log.d(TAG, "disconnectLocationRequest: ")
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)
    }

    companion object {
        /**
         * Declared [LocationManager] member variables
         */
        private const val TAG = "LocationManager"
        private const val LOCATION_INTERVAL: Long = 5000
        private const val FASTEST_LOCATION_INTERVAL: Long = 2000
    }

    init {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                Log.d(TAG, "connectLocationRequest: " + locationResult.getLocations())
                for (location in locationResult.getLocations()) {
                    if (location != null) {
                        Log.d(
                            TAG,
                            "connectLocationProvider: $location"
                        )
                        //                        setValue(location);
                        if (mBestLocation == null) mBestLocation =
                            location else if (mBestLocation!!.accuracy > location.accuracy) mBestLocation =
                            location
                        mCurrentCount++
                        if (location.accuracy <= Constants.LOCATION_ACCURACY_THRESHOLD) value =
                            location else if (mCurrentCount >= MAX_RETRY) {
                            mBestLocation!!.elapsedRealtimeNanos = location.elapsedRealtimeNanos
                            value = mBestLocation
                        }
                    }
                }
            }
        }
    }
}