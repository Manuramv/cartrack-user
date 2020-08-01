package com.cartrack.users.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern


object ValidationRule {
    @JvmStatic
    var PASSWORD_RULE: StringRule = object : StringRule {
        override fun validate(s: String?): Boolean {
            val PWD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{6,15}$";
            val patt: Pattern = Pattern.compile(PWD_PATTERN)
            val matcher: Matcher = patt.matcher(s)
            return matcher.matches()
        }

        override fun validationErrorMsg(): String {
            return "Please enter valid password"
        }
    }
    @JvmStatic
    var EMAIL_RULE: StringRule = object : StringRule {
        override fun validate(s: String?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(s).matches()
        }

        override fun validationErrorMsg(): String {
            return "Please enter valid email"
        }
    }
}
interface StringRule {
    fun validate(s: String?): Boolean
    fun validationErrorMsg():String
}
