package com.example.kode_introductory_task.domain

import com.example.kode_introductory_task.domain.repository.Repository

class GetWorkersListUseCase (private val repository: Repository) {
    suspend operator fun invoke() = repository.getWorkersList()
}