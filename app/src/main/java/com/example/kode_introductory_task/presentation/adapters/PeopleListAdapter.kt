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
    lateinit var fullList: List<Worker>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val binding =
            UsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    //Обновляет текущий список, либо текущий и список всей вкладки
    fun submitList(list: List<Worker>?, updateFullList: Boolean) {
        super.submitList(list)
        if (updateFullList && !list.isNullOrEmpty()) {
            fullList = list
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            //Работает в фоновом потоке
            override fun performFiltering(searchingStr: CharSequence?) =
                searchingStr?.let {
                    FilterResults().apply {
                        values = fullList.filter {
                            it.firstName.lowercase().contains((searchingStr as String).lowercase()) ||
                                    it.lastName.lowercase().contains(searchingStr.lowercase())
                        }
                    }
                } ?: FilterResults().apply {
                    values = fullList
                }


            //Работает в UI потоке
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                submitList(results?.values as List<Worker>, false)
            }
        }
    }
}