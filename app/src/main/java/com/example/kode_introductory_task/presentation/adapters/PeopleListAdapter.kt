package com.example.kode_introductory_task.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import com.example.kode_introductory_task.databinding.UsersListItemBinding
import com.example.kode_introductory_task.domain.Worker
import com.squareup.picasso.Picasso

class PeopleListAdapter : ListAdapter<Worker, WorkerViewHolder>(WorkersDiffCallBack()), Filterable {

    var clickListener: ((Worker) -> Unit)? = null

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
        holder.binding.root.setOnClickListener {
            clickListener?.invoke(worker)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            //Работает в фоновом потоке
            override fun performFiltering(p0: CharSequence?): FilterResults {
                TODO("Not yet implemented")
            }

            //Работает в UI потоке
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                TODO("Not yet implemented")
            }
        }
    }
}