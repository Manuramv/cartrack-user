package com.cartrack.users.ui.home.userdetail

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController

import com.cartrack.users.R
import com.cartrack.users.data.model.UserListResponseItem
import com.cartrack.users.databinding.FragmentUserDetailsBinding
import com.cartrack.users.utils.PermissionUtils
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
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

        MapsInitializer.initialize(this.activity)


        val mapView = binding.mapView
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this)
        mapView.onResume();
        setupObserver()



        return binding.root

    }

    private fun setupObserver() {
        userDetailsViewModel.mMapLatLng.observe(viewLifecycleOwner, Observer {
           /* try {
                MapsInitializer.initialize(this.activity)
                val sydney = LatLng(1.4173, 103.8330)
                val icon = BitmapDescriptorFactory.fromResource(R.drawable.marker)
                var markerOptions = MarkerOptions().position(sydney).icon(icon).title(it.userName+"is here").visible(true)
                mMap?.addMarker(markerOptions)?.showInfoWindow()
                mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,18F))
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            }*/
        })


        userDetailsViewModel.phoneCall.observe(viewLifecycleOwner, Observer {
        })

        userDetailsViewModel.email.observe(viewLifecycleOwner, Observer {
            sendEmail(it)
        })

        userDetailsViewModel.website?.observe(viewLifecycleOwner, Observer {
            openWebsite(it)

        })


    }

    private fun openWebsite(webAddress: String?) {
        Log.d("usrdetailsfrag","calling openwebsite")
        findNavController().navigate(
            R.id.action_userDetailFragment_to_webViewFragment,
            bundleOf("website" to webAddress)
        )
    }

    //sendEmail
    private fun sendEmail(emailId: String?) {
        val email =  Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, emailId)
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
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




    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        userDetailsViewModel.setNewLocation()

    }






}
