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


