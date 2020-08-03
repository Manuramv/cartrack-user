package com.cartrack.users.ui.webview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.Navigator
import com.cartrack.users.data.model.UserListResponseItem
import com.cartrack.users.databinding.FragmentWebViewBinding
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.MapsInitializer


/**
 * A simple [Fragment] subclass.
 */
@Navigator.Name("WebViewFragment")
class WebViewFragment : Fragment() {
    private val TAG = WebViewFragment::class.java.canonicalName
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("website")?.let {
           loadWebsite("www.google.com")
       }
    }



    fun loadWebsite(webAddress: String) {
        Log.d(TAG,"website url::"+webAddress)
        /*binding.webView.loadUrl(webAddress)
        val webSettings: WebSettings = binding.webView.getSettings()
        webSettings.javaScriptEnabled = true
        binding.webView.setWebViewClient(WebViewClient())*/
    }

}
