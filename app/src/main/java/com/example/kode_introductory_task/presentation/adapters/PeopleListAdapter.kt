package com.example.kode_introductory_task.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kode_introductory_task.databinding.UsersListItemBinding
import com.example.kode_introductory_task.domain.Worker
import com.squareup.picasso.Picasso

class PeopleListAdapter : ListAdapter<Worker, WorkerViewHolder>(WorkersDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val binding = UsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        val worker = getItem(position)
        val fullName = StringBuilder(worker.firstName + " ").append(worker.lastName)
        holder.binding.fullNameTv.text = fullName
        holder.binding.position.text = worker.position
        holder.binding.meta.text = worker.userTag
        Picasso.get().load(worker.avatarUrl).into(holder.binding.personImage)
    }
}