package com.example.kode_introductory_task.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kode_introductory_task.DetailInfoFragment
import com.example.kode_introductory_task.R
import com.example.kode_introductory_task.databinding.FragmentUsersListBinding
import com.example.kode_introductory_task.presentation.adapters.PeopleListAdapter
import com.google.android.material.tabs.TabLayout

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[UsersListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PeopleListAdapter()
        binding.workersRv.adapter = adapter
        viewModel.workersList.observe(viewLifecycleOwner) { list ->
            //Временная заглушка для фото, пока сломаны ссылки
            list.map {
                it.avatarUrl =
                    "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg"
            }
            adapter.submitList(list, true)
        }

        adapter.clickListener = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, DetailInfoFragment.newInstance(it))
                .addToBackStack(null)
                .commit()
        }


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
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

            override fun afterTextChanged(searhingStr: Editable?) {}

            override fun onTextChanged(searhingStr: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    adapter.filter.filter(searhingStr.toString())
            }
        })

        binding.editTextName.setOnFocusChangeListener { _, isFocused ->
            binding.cancelButton.visibility = View.VISIBLE
            when(isFocused) {
                true -> (view as EditText).hint = ""
                false -> (view as EditText).hint = getString(R.string.input_hint)
            }
        }

        binding.cancelButton.setOnClickListener {
            binding.editTextName.clearFocus()
            binding.editTextName.setText("")
            it.visibility = View.GONE
            //Keyboard Hiding
            val imm = requireContext()
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}