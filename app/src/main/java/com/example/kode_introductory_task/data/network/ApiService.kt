package com.example.kode_introductory_task.data.network

import com.example.kode_introductory_task.data.network.model.WorkersContainerDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getWorkersList(): WorkersContainerDto
}