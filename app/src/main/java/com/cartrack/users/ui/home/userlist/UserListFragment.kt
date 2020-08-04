package com.cartrack.users.ui.home.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.cartrack.users.R
import com.cartrack.users.data.model.UserListResponseItem
import com.cartrack.users.data.repository.UserRepository
import com.cartrack.users.databinding.FragmentUserListBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment(),UserListAdapter.UserItemListner{
    private lateinit var binding: FragmentUserListBinding
    private lateinit var userListViewModel: UserListViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        //call the custome viewmodel factory and get the repo instance in viewmodel
        userListViewModel = ViewModelProvider(this, UserListViewModelFactory(UserRepository(), UserListViewModel::class.java ))
            .get(UserListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = userListViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.getUsersList()
        setupRecyclerView()
        setupObservers()
    }


    //set the user list recyclerview
    private fun setupRecyclerView() {
        adapter = UserListAdapter(this)
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter
        binding.rvUsers.setItemAnimator(DefaultItemAnimator())
    }

    //Observe for the userdata - once we get the user data from API set it to adapter
    private fun setupObservers() {
        userListViewModel.usersData.observe(viewLifecycleOwner, Observer {
            adapter.setItems(ArrayList(it))
        })
    }

    override fun onClickedUser(user: UserListResponseItem) {
        findNavController().navigate(
            R.id.action_userFragment_to_userDetailFragment,
            bundleOf("user" to user)
        )
    }



}
