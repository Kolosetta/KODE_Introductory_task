package com.example.kode_introductory_task.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kode_introductory_task.data.repository.RepositoryImpl
import com.example.kode_introductory_task.domain.GetWorkersListUseCase
import com.example.kode_introductory_task.domain.Worker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersListViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    private val getWorkersListUseCase = GetWorkersListUseCase(repository)

    private val _workersList = MutableLiveData<List<Worker>>()
    val workersList: LiveData<List<Worker>>
        get() = _workersList

    init{
        viewModelScope.launch(){
            _workersList.value = withContext(Dispatchers.IO){
                getWorkersListUseCase.invoke()
            }
        }
    }

}