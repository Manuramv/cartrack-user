package com.cartrack.users.utils

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationUtils {
    fun isEmailValid(emailAddress: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

    fun isPwdValid(password: String): Boolean{
        val PWD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{6,15}$";
        val patt: Pattern = Pattern.compile(PWD_PATTERN)
        val matcher: Matcher = patt.matcher(password)
        return matcher.matches()
    }
}