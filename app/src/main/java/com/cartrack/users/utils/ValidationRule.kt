package com.cartrack.users.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern


object ValidationRule {
    @JvmStatic
    var PASSWORD_RULE: StringRule = object : StringRule {
        var isValidFiled = false
        override fun validate(s: String?): Boolean {
            val PWD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=?-]).{6,15}$";
            val patt: Pattern = Pattern.compile(PWD_PATTERN)
            val matcher: Matcher = patt.matcher(s)
            return matcher.matches()
        }

        override fun validationErrorMsg(): String {
            return "Please enter valid password"
        }

        override fun setValid(isvalid: Boolean): Boolean {
            isValidFiled = isvalid
            isValid()
            return isvalid
        }
        override fun isValid(): Boolean {
            return isValidFiled
        }
    }
    @JvmStatic
    var EMAIL_RULE: StringRule = object : StringRule {
        var isValidFiled = false
        override fun validate(s: String?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(s).matches()
        }

        override fun validationErrorMsg(): String {
            return "Please enter valid email"
        }

        override fun isValid(): Boolean {
            return isValidFiled
        }

        override fun setValid(isvalid: Boolean): Boolean {
            isValidFiled = isvalid
            isValid()
            return isvalid
        }

    }
}
interface StringRule {
    fun validate(s: String?): Boolean
    fun validationErrorMsg():String
    fun isValid(): Boolean
    fun setValid(isvalid: Boolean): Boolean
}
