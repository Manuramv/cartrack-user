package com.cartrack.users.ui.home.userdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cartrack.users.data.model.UserGeoData
import com.cartrack.users.data.model.UserListResponseItem


class UserDetailsViewModel: ViewModel() {
    val TAG = UserDetailsViewModel::class.java.canonicalName
    var mMapLatLng= MutableLiveData<UserGeoData> ()

    var userDetail= MutableLiveData<UserListResponseItem> ()
    private lateinit var mapLocation : UserGeoData


    //start the userdetails screen
    fun start(it: UserListResponseItem) {
        Log.d(TAG,"values::"+it.name)
        mapLocation = UserGeoData(it.address.geo.lat, it.address.geo.lng,it.name.split(" ").get(0),it.address.street,
            it.address.city,it.address.zipcode)
        mMapLatLng.value = mapLocation
        userDetail.value = it
    }

    fun setNewLocation() {
        Log.d(TAG,"values viewmodel::latLng?.lat=="+mapLocation.lat+"lang.."+mapLocation.lang)
        mMapLatLng.value = mapLocation
    }
}