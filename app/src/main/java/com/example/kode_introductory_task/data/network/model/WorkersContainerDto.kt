package com.example.kode_introductory_task.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WorkersContainerDto(
    @SerializedName("items")
    @Expose
    val items: List<WorkerDto>
)
