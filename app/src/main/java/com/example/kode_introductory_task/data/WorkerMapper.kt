package com.example.kode_introductory_task.data

import com.example.kode_introductory_task.data.network.model.WorkerDto
import com.example.kode_introductory_task.domain.Worker

class WorkerMapper {

    fun mapWorkerDtoToEntity(workerDto: WorkerDto) : Worker =
        Worker(
            workerDto.id,
            workerDto.avatarUrl,
            workerDto.firstName,
            workerDto.lastName,
            workerDto.userTag,
            workerDto.department,
            workerDto.position,
            workerDto.birthday,
            workerDto.phone
        )
}