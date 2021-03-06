package com.cartrack.users.ui.adapters

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.cartrack.users.utils.StringRule
import com.google.android.material.textfield.TextInputLayout


object TextInputBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:validation", "app:errorMsg" )
    fun setErrorEnable(textInputLayout: TextInputLayout?, stringRule: StringRule, errorMsg: String?) {

        textInputLayout?.editText?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(!stringRule.validate(s.toString()) && !TextUtils.isEmpty(s)){
                    stringRule.setValid(false)
                    stringRule.isValid()
                    textInputLayout.error = stringRule.validationErrorMsg()
                } else {
                    stringRule.setValid(true)
                    stringRule.isValid()
                    textInputLayout.error = ""

                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

}