package com.cartrack.users.ui.home.userlist

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cartrack.users.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView,  url: String?) {
    Glide.with(view.context).load(R.drawable.ic_person_blue_24dp)
        .into(view);
}

@BindingAdapter("countryImageUrl")
fun loadCountryImage(view: ImageView,  url: String?){
    Glide.with(view.context).load(generateImgUrl(url))
        .error(R.drawable.img_loader)
        .placeholder(R.drawable.img_loader)
        .into(view);
}
//generating the url to fetch the country image
fun generateImgUrl(imgCountryCode: String?): String {
    val imgBaseUrl = "https://www.countryflags.io/"
    return imgBaseUrl + imgCountryCode?.toLowerCase() + "/flat/64.png"

}



