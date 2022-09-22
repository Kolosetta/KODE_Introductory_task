package com.example.kode_introductory_task

import android.content.Intent
import android.net.Uri
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
        val worker = requireArguments().getParcelable<Worker>(WORKER_INFO)
        binding.worker = worker
        setOnClickListeners(worker)
    }

    private fun setOnClickListeners(worker: Worker?){
        binding.phoneLayout.setOnClickListener {
            val uri = String.format(
                getString(R.string.intent_phone_sample),
                worker?.phone
            )
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data =  Uri.parse(uri)
            }
            startActivity(intent)
        }
        binding.backButton.setOnClickListener{
            //TODO Добавить анимацию нажатия
            //val animAlpha = AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_in);
            //it.startAnimation(animAlpha)
            //TODO Добавить анимации переходов
            requireActivity().supportFragmentManager.popBackStack()

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