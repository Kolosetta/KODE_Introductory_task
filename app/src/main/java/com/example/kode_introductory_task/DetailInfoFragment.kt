package com.example.kode_introductory_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kode_introductory_task.databinding.FragmentDetailInfoBinding
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
        binding.worker = requireArguments().getParcelable(WORKER_INFO)
        requireArguments().getParcelable<Worker>(WORKER_INFO)?.let {
            /*binding.fullNameTv.text = StringBuilder(it.firstName+ " ").append(it.lastName)
            binding.meta.text = it.userTag
            binding.position.text = it.position
            binding.phoneNumber.text = it.phone

            val birthDate = it.birthday
            val simpleFormatter = SimpleDateFormat("yyyy-MM-dd").parse(birthDate)
            val textDate = DateFormat.getDateInstance(SimpleDateFormat.LONG,  Locale("ru"))
                .format(simpleFormatter)
            binding.birthDate.text = textDate

            val periodFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val period = Period.between(LocalDate.parse(birthDate, periodFormatter), LocalDate.now())
            binding.age.text = period.years.toString()*/

           /* val birthDate = it.birthday

            val df = android.icu.text.DateFormat.getDateInstance(DateFormat.YEAR_FIELD)
            val simpleFormatter = df.parse(birthDate)
            val textDate = android.icu.text.DateFormat.getDateInstance(DateFormat.LONG,  Locale("ru"))
                .format(simpleFormatter)
            binding.birthDate.text = textDate

            val pattern = "MM-dd-yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)*/
        }
    }

    companion object {
        private const val WORKER_INFO = "WORKER_INFO"

        fun newInstance(worker: Worker) =
            DetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(WORKER_INFO, worker)
                }
            }
    }
}