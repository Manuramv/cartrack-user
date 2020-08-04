package com.cartrack.users.ui.home.userdetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cartrack.users.data.model.UserGeoData
import com.cartrack.users.databinding.CustominfowindowBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker


class CustomMapInfoWindowAdapter(val context: AppCompatActivity) :GoogleMap.InfoWindowAdapter{
    override fun getInfoContents(marker: Marker?): View {

        val binding = CustominfowindowBinding.inflate(LayoutInflater.from(context), null)
        val userInfo = marker?.tag as UserGeoData
        Log.d("tag","user info custom marker::"+userInfo)
        binding.tvTitle.text = userInfo.userName
        binding.tvLatLang.text = userInfo.lat.toString() +", "+ userInfo.lang
        binding.tvAptCity.text = userInfo.street +", "+ userInfo.city
        binding.tvZipcode.text = "Zip:"+userInfo.zipCode
        return binding.root

    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }

}