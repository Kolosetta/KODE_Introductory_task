package com.example.kode_introductory_task.domain.repository

class GetWorkerInfoUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getWorkerInfo()
}