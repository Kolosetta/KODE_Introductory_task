package com.example.kode_introductory_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kode_introductory_task.databinding.FragmentDetailInfoBinding
import com.example.kode_introductory_task.databinding.FragmentUsersListBinding
import com.example.kode_introductory_task.domain.Worker


class DetailInfoFragment : Fragment() {
    private lateinit var binding: FragmentDetailInfoBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = requireArguments()
        val fullName = StringBuilder(arguments.getString(FIRST_NAME)+ " ")
            .append(arguments.getString(LAST_NAME))
        binding.fullNameTv.text = fullName
        binding.meta.text = arguments.getString(TAG)
        binding.position.text = arguments.getString(POSITION)
        binding.birthDate.text = arguments.getString(BIRTH_DATE)
        binding.phoneNumber.text = arguments.getString(PHONE_NUMBER)
    }

    companion object {
        private const val FIRST_NAME = "FIRST_NAME"
        private const val LAST_NAME = "LAST_NAME"
        private const val TAG = "TAG"
        private const val POSITION = "POSITION"
        private const val BIRTH_DATE = "BIRTH_DATE"
        private const val PHONE_NUMBER = "PHONE_NUMBER"
        private const val PERSON_AVATAR = "PERSON_AVATAR"

        fun newInstance(worker: Worker) =
            DetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(FIRST_NAME, worker.firstName)
                    putString(LAST_NAME, worker.lastName)
                    putString(TAG, worker.userTag)
                    putString(POSITION, worker.position)
                    putString(BIRTH_DATE, worker.birthday)
                    putString(PHONE_NUMBER, worker.phone)
                    putString(PERSON_AVATAR, worker.avatarUrl)
                }
            }
    }
}