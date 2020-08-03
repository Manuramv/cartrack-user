package com.cartrack.users.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

object PermissionUtils {
    const val PHONE_CALL_REQPERMISSION = 118
    fun checkPermission(context: AppCompatActivity): Boolean {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context,
                    arrayOf(Manifest.permission.CALL_PHONE), PHONE_CALL_REQPERMISSION)
            }
        } else {
            // Permission has already been granted
            return true
        }
        return false
    }

}