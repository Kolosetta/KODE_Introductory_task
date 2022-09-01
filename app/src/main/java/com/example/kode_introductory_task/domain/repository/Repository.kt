package com.example.kode_introductory_task.domain.repository

import com.example.kode_introductory_task.domain.Worker

interface Repository {
    fun getWorkersList(): List<Worker>
    fun getWorkerInfo(): Worker
}