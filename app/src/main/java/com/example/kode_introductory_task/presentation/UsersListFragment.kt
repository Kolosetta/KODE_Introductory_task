package com.example.kode_introductory_task.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kode_introductory_task.DetailInfoFragment
import com.example.kode_introductory_task.R
import com.example.kode_introductory_task.databinding.FragmentUsersListBinding
import com.example.kode_introductory_task.presentation.adapters.PeopleListAdapter

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[UsersListViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PeopleListAdapter()
        binding.workersRv.adapter = adapter
        viewModel.workersList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        adapter.clickListener = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, DetailInfoFragment.newInstance(it))
                .addToBackStack(null)
                .commit()
        }
    }
}