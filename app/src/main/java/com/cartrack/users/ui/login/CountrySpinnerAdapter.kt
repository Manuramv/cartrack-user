package com.cartrack.users.ui.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.cartrack.users.R
import com.cartrack.users.data.model.CountryListItem
import com.cartrack.users.databinding.CountryCustomSpinnerBinding


//adapter to show the country spinner
class CountrySpinnerAdapter(context: Context, resource: Int, private val values: List<CountryListItem>) :
    ArrayAdapter<CountryListItem>(context, resource, values) {

    override fun getCount() = values.size
    override fun getItem(position: Int) = values[position]



    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
         val binding = DataBindingUtil.inflate<CountryCustomSpinnerBinding>(
            LayoutInflater.from(context),
            R.layout.country_custom_spinner,
            parent, false
        ).also {
            it.root.tag = it
        }
        binding.adapterdata = values[position]
        binding.txtCountryName.text = values[position].name
        return binding.root
    }
    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<CountryCustomSpinnerBinding>(
            LayoutInflater.from(context),
            R.layout.country_custom_spinner,
            parent, false
        ).also {
            it.root.tag = it
        }

        binding.adapterdata = values[position]
        binding.txtCountryName.text = values[position].name
        return binding.root
    }
}