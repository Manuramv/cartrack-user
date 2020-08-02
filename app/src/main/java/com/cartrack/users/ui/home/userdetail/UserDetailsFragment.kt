package com.cartrack.users.ui.home.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cartrack.users.R
import com.cartrack.users.data.model.UserListResponseItem
import com.cartrack.users.databinding.FragmentUserDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserDetailsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentUserDetailsBinding
    private  val userDetailsViewModel: UserDetailsViewModel by viewModels()
    private var mMap: GoogleMap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val mapView = binding.mapView
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this)
        mapView.onResume();

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<UserListResponseItem>("user")?.let {
            userDetailsViewModel.start(it)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        userDetailsViewModel.setNewLocation()

        val sydney = LatLng(1.4173, 103.8330)
        val icon = BitmapDescriptorFactory.fromResource(R.drawable.marker)
        var markerOptions = MarkerOptions().position(sydney).icon(icon).title("marker viisble").visible(true)

        mMap?.addMarker(markerOptions)?.showInfoWindow()
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,18F))
    }


}
