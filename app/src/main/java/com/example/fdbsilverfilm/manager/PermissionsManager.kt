package com.example.fdbsilverfilm.manager

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


object PermissionsManager {
    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )
    private var request_code = 34

    /**
     * Check all permissions of the application.
     * @param context Context of the activity.
     * @return true if all permissions are granted, false else.
     */
    fun checkPermissions(context: Context): Boolean {
        for (permission in PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_DENIED
            )
                return false
        }
        return true
    }

    /**
     * Ask for all permissions of the application
     * @param activity The activity where is ask the permissions
     */
    fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(activity, PERMISSIONS, request_code)
    }
}