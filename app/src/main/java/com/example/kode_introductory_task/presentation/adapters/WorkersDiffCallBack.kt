package com.example.kode_introductory_task.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.kode_introductory_task.domain.Worker

class WorkersDiffCallBack : DiffUtil.ItemCallback<Worker>() {
    override fun areItemsTheSame(oldItem: Worker, newItem: Worker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Worker, newItem: Worker): Boolean {
        return oldItem == newItem
    }
}