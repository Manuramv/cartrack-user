package com.cartrack.users.ui.home.userlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cartrack.users.data.model.UserListResponseItem
import com.cartrack.users.databinding.UserItemBinding

class UserListAdapter(private val listener: UserItemListner) : RecyclerView.Adapter<UserViewHolder>() {

    interface UserItemListner {
        fun onClickedUser(userId: Int)
    }

    private val items = ArrayList<UserListResponseItem>()

    fun setItems(items: ArrayList<UserListResponseItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: UserItemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(items[position])
}

class UserViewHolder(private val itemBinding: UserItemBinding, private val listener: UserListAdapter.UserItemListner) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var user: UserListResponseItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: UserListResponseItem) {
        this.user = item
        itemBinding.adapterdata = item
    }

    override fun onClick(v: View?) {
        listener.onClickedUser(user.id)
    }
}

