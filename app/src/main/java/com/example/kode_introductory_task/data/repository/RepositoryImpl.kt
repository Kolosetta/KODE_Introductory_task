package com.example.kode_introductory_task.data.repository

import com.example.kode_introductory_task.data.WorkerMapper
import com.example.kode_introductory_task.data.network.ApiFactory
import com.example.kode_introductory_task.domain.Worker
import com.example.kode_introductory_task.domain.repository.Repository

class RepositoryImpl : Repository {

    val mapper = WorkerMapper()

    override suspend fun getWorkersList(): List<Worker> {
        return ApiFactory.apiService.getWorkersList().items.map {
            mapper.mapWorkerDtoToEntity(it)
        }
    }

    override fun getWorkerInfo(): Worker {
        TODO("Not yet implemented")
    }
}