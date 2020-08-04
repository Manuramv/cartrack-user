package com.cartrack.users.ui.login

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.cartrack.users.data.model.CountryListItem
import com.cartrack.users.ui.login.SpinnerExtensions.setSpinnerEntries
import com.cartrack.users.ui.login.SpinnerExtensions.setSpinnerItemSelectedListener
import com.cartrack.users.ui.login.SpinnerExtensions.setSpinnerValue



object SpinnerBindings {

    @JvmStatic
    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: List<CountryListItem>?) {
        setSpinnerEntries(entries)
    }

    @JvmStatic
    @BindingAdapter("onItemSelected")
    fun Spinner.setOnItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @JvmStatic
    @BindingAdapter("newValue")
    fun Spinner.setNewValue(newValue: CountryListItem?) {
        setSpinnerValue(newValue)
    }
}