package com.cartrack.users.ui.home.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.cartrack.users.data.repository.UserRepository
import com.cartrack.users.databinding.FragmentUserListBinding
import com.cartrack.users.utils.Resource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment(),UserListAdapter.UserItemListner {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var userListViewModel: UserListViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        userListViewModel = ViewModelProvider(this, ViewModelFactory(UserRepository(), UserListViewModel::class.java ))
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


    private fun setupRecyclerView() {
        adapter = UserListAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        userListViewModel.usersData.observe(viewLifecycleOwner, Observer {
            adapter.setItems(ArrayList(it))
        })
    }

    override fun onClickedUser(characterId: Int) {
    }


}
