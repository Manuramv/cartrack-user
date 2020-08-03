package com.cartrack.users.ui.home.userdetail

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cartrack.users.data.model.LatLang
import com.cartrack.users.data.model.UserListResponseItem


class UserDetailsViewModel: ViewModel() {
    val TAG = UserDetailsViewModel::class.java.canonicalName
    var mMapLatLng= MutableLiveData<LatLang> ()
    var phoneCall = MutableLiveData<String>()
    var email = MutableLiveData<String>()

    var userDetail= MutableLiveData<UserListResponseItem> ()
    private lateinit var mapLocation : LatLang

    fun start(it: UserListResponseItem) {
        Log.d(TAG,"values::"+it.name)
        mapLocation = LatLang(it.address.geo.lat, it.address.geo.lng,it.name.split(" ").get(0))
        mMapLatLng.value = mapLocation
        userDetail.value = it
    }

    fun setNewLocation() {
        Log.d(TAG,"values viewmodel::latLng?.lat=="+mapLocation.lat+"lang.."+mapLocation.lang)
        mMapLatLng.value = mapLocation
    }


    fun onclickPhoneCall(phoneNo: String){
       // phoneCall.value = phoneNo
    }
    fun onclickEmail(userEmail: String){
        email.value = userEmail
    }
}