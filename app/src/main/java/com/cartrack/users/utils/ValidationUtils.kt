package com.cartrack.users.utils

import android.util.Patterns

object ValidationUtils {
    fun isFormValid(emailAddress: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }
}