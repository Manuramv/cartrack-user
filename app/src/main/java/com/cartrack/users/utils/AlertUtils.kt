package com.cartrack.users.utils

import android.app.AlertDialog
import android.content.Context


object AlertUtils {
    fun showAlert(context: Context, title: String, msg:String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }
}