package com.example.kode_introductory_task.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Worker(
    val id: String,
    //TODO заменить на val, когда починят ссылки на сервере
    var avatarUrl: String,
    val firstName: String,
    val lastName: String,
    val userTag: String,
    val department: String,
    val position: String,
    val birthday: String,
    val phone: String
) : Parcelable
