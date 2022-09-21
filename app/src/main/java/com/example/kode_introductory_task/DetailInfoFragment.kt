package com.example.kode_introductory_task

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.kode_introductory_task.databinding.FragmentDetailInfoBinding
import com.example.kode_introductory_task.domain.Worker
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*


class DetailInfoFragment : Fragment() {
    private lateinit var binding: FragmentDetailInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = requireArguments()
        val fullName = StringBuilder(arguments.getString(FIRST_NAME)+ " ")
            .append(arguments.getString(LAST_NAME))
        val fullNumber = StringBuilder("+7 ").append(arguments.getString(PHONE_NUMBER))
        binding.fullNameTv.text = fullName
        binding.meta.text = arguments.getString(TAG)
        binding.position.text = arguments.getString(POSITION)
        binding.phoneNumber.text = fullNumber

        val date = arguments.getString(BIRTH_DATE) ?: "1970.01.01"
        val simpleFormatter = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val textDate = DateFormat.getDateInstance(SimpleDateFormat.LONG,  Locale("ru"))
            .format(simpleFormatter)

        val periodFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDate = LocalDate.parse(date, periodFormatter)
        val endDate = LocalDate.now()
        val period = Period.between(startDate, endDate)
        binding.age.text = period.years.toString()
        binding.birthDate.text = textDate
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