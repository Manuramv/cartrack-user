package com.cartrack.users.ui.home.userdetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController

import com.cartrack.users.R
import com.cartrack.users.data.model.UserGeoData
import com.cartrack.users.data.model.UserListResponseItem
import com.cartrack.users.databinding.FragmentUserDetailsBinding
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



@Navigator.Name("UserDetailsFragment")
class UserDetailsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentUserDetailsBinding
    private  lateinit var userDetailsViewModel: UserDetailsViewModel
    private var mMap: GoogleMap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        userDetailsViewModel = ViewModelProvider(this).get(UserDetailsViewModel::class.java)
        binding.viewModel = userDetailsViewModel
        binding.lifecycleOwner = this

        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.getMapAsync(this)
        binding.mapView.onResume();

        setupObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            MapsInitializer.initialize(this.activity)
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }

        arguments?.getParcelable<UserListResponseItem>("user")?.let {
            userDetailsViewModel.start(it)
        }
    }




    private fun setupObserver() {
        userDetailsViewModel.mMapLatLng.observe(viewLifecycleOwner, Observer {
           try {
                MapsInitializer.initialize(this.activity)
                setTheMapMarkerandInfo(it)
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            }
        })
    }

    //set the map marker and custom info windo
    private fun setTheMapMarkerandInfo(it: UserGeoData) {
        val icon = BitmapDescriptorFactory.fromResource(R.drawable.marker)
        var markerOptions = MarkerOptions().position(LatLng(it.lat!!, it.lang!!)).icon(icon).title(it.userName+" is here").visible(true)
        val marker = mMap?.addMarker(markerOptions)
        marker?.tag = it
        marker?.showInfoWindow()
        mMap?.setInfoWindowAdapter(CustomMapInfoWindowAdapter(this.activity as AppCompatActivity))
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.lang),18F))
    }

    private fun openWebsite(webAddress: String?) {
        Log.d("usrdetailsfrag","calling openwebsite")
        findNavController().navigate(
            R.id.action_userDetailFragment_to_webViewFragment,
            bundleOf("website" to webAddress)
        )
    }



    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        userDetailsViewModel.setNewLocation()

    }


    override fun onDestroy() {
        super.onDestroy()
        mMap =null
    }




}
