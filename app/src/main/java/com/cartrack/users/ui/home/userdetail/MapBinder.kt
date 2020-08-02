package com.cartrack.users.ui.home.userdetail

import android.os.Bundle
import android.util.Log

import androidx.databinding.BindingAdapter
import com.cartrack.users.data.model.LatLang
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


@BindingAdapter("initMap")
fun initMap(mapView: MapView?, latLng: LatLang?) {
    if (mapView != null) {
        mapView.onCreate(Bundle())
        mapView.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) { // Add a marker
                Log.d("initMap","latLng?.lat="+latLng?.lat+"..latLng?.lan="+latLng?.lang)
                val newLatLang = LatLng(68.6102, -47.0653)
                val sydney = LatLng(-34.0, 151.0)
                googleMap?.addMarker(MarkerOptions().position(sydney).title("Marker in manu"))
                googleMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))


                //googleMap.addMarker(MarkerOptions().position(newLatLang).title("Marker in India"))
            }
        })
    }
}
