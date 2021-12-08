package com.example.fdbsilverfilm.manager

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


object PermissionsManager {
    private val permissions : ArrayList<String> = ArrayList()
    private var request_code = 0

    //Add permissions you have to check
    init {
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    /**
     * Check all permissions of the applications
     * @param context Context of the activity.
     * @return true if all permissions are granted, false else.
     */
    fun checkPermissions(context: Context) : Boolean {
        for (permission in permissions){
            if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED)
                return false
        }
        return true
    }

    /**
     * Ask for all permissions of the application
     * @param activity The activity where is ask the permissions
     */
    fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(activity, permissions.toArray() as Array<out String>, request_code)
    }
}