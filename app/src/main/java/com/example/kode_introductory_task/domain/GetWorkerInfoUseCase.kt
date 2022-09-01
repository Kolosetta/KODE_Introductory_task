package com.example.kode_introductory_task.domain

import com.example.kode_introductory_task.domain.repository.Repository

class GetWorkerInfoUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getWorkerInfo()
}