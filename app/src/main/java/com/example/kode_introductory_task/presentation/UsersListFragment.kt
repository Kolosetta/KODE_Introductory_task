package com.example.kode_introductory_task.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kode_introductory_task.DetailInfoFragment
import com.example.kode_introductory_task.R
import com.example.kode_introductory_task.databinding.FragmentUsersListBinding
import com.example.kode_introductory_task.domain.Worker
import com.example.kode_introductory_task.presentation.adapters.PeopleListAdapter
import com.google.android.material.tabs.TabLayout

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[UsersListViewModel::class.java]
    }
    private lateinit var workersList: List<Worker>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PeopleListAdapter()
        binding.workersRv.adapter = adapter
        viewModel.workersList.observe(viewLifecycleOwner){ list ->
            //Временная заглушка для фото, пока сломаны ссылки
            list.map{
                it.avatarUrl = "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg"
            }
            adapter.submitList(list)
        }

        adapter.clickListener = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, DetailInfoFragment.newInstance(it))
                .addToBackStack(null)
                .commit()
        }

        //TODO дописать реализацию
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.text){
                    "Designers" -> viewModel.getNewList("design")
                    "Android" -> viewModel.getNewList("android")
                    "Analytics" -> viewModel.getNewList("analytics")
                    "HR" -> viewModel.getNewList("hr")
                    //"Главная" -> { startMainListFragment() }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}