package com.example.kode_introductory_task.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WorkerDto(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("avatarUrl")
    @Expose
    val avatarUrl: String,
    @SerializedName("firstName")
    @Expose
    val firstName: String,
    @SerializedName("lastName")
    @Expose
    val lastName: String,
    @SerializedName("userTag")
    @Expose
    val userTag: String,
    @SerializedName("department")
    @Expose
    val department: String,
    @SerializedName("position")
    @Expose
    val position: String,
    @SerializedName("birthday")
    @Expose
    val birthday: String,
    @SerializedName("phone")
    @Expose
    val phone: String
)
