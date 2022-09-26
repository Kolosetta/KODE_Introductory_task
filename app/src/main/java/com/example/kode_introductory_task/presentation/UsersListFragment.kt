package com.example.kode_introductory_task.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.text){
                    "Android" -> viewModel.getWorkersByDepartment("android")
                    "iOS" -> viewModel.getWorkersByDepartment("ios")
                    "Designers" -> viewModel.getWorkersByDepartment("design")
                    "Managers" -> viewModel.getWorkersByDepartment("management")
                    "QA" -> viewModel.getWorkersByDepartment("qa")
                    "Analysts" -> viewModel.getWorkersByDepartment("analytics")
                    "Back Office" -> viewModel.getWorkersByDepartment("back_office")
                    "Frontend" -> viewModel.getWorkersByDepartment("frontend")
                    "HR" -> viewModel.getWorkersByDepartment("hr")
                    "PR" -> viewModel.getWorkersByDepartment("pr")
                    "Backend" -> viewModel.getWorkersByDepartment("backend")
                    "Support" -> viewModel.getWorkersByDepartment("support")
                    "Все" -> viewModel.getWorkersByDepartment("")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                adapter.filter.filter(p0)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}