package com.cartrack.users.ui.login


import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.InverseBindingListener
import com.cartrack.users.R
import com.cartrack.users.data.model.CountryListItem


object SpinnerExtensions {

    /**
     * set spinner entries
     */
    @JvmStatic
    fun Spinner.setSpinnerEntries(entries: List<CountryListItem>?) {
        if (entries != null) {
            val arrayAdapter = CountrySpinnerAdapter(context, R.layout.country_custom_spinner, entries)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = arrayAdapter
        }
    }

    /**
     * set spinner onItemSelectedListener listener
     */
    @JvmStatic
    fun Spinner.setSpinnerItemSelectedListener(listener: ItemSelectedListener?) {
        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        listener.onItemSelected(parent.getItemAtPosition(position) as CountryListItem)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    /**
     * set spinner onItemSelectedListener listener
     */
    @JvmStatic
    fun Spinner.setSpinnerInverseBindingListener(listener: InverseBindingListener?) {
        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        listener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    /**
     * set spinner value
     */
    fun Spinner.setSpinnerValue(value: CountryListItem?) {
        if (adapter != null ) {
            val position = (adapter as ArrayAdapter<CountryListItem>).getPosition(value)
            setSelection(position, false)
            tag = position
        }
    }

    /**
     * get spinner value
     */
    fun Spinner.getSpinnerValue(): CountryListItem? {
        return selectedItem as CountryListItem
    }

    interface ItemSelectedListener {
        fun onItemSelected(item: CountryListItem)
    }
}