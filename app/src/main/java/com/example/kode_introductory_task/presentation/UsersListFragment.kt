package com.example.kode_introductory_task.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kode_introductory_task.data.repository.RepositoryImpl
import com.example.kode_introductory_task.databinding.FragmentUsersListBinding
import com.example.kode_introductory_task.domain.Worker
import com.example.kode_introductory_task.presentation.adapters.PeopleListAdapter
import kotlinx.coroutines.*

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding
    private val repository = RepositoryImpl()
    private lateinit var internetList: List<Worker>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PeopleListAdapter()
        binding.workersRv.adapter = adapter
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)
        uiScope.launch(Dispatchers.IO){
            //asyncOperation
            internetList = repository.getWorkersList()
            withContext(Dispatchers.Main){
                adapter.submitList(internetList)
            }

        }
        /*val list = listOf(
            Worker("123", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Stepan","Mohov","mi","asd","Senior","asd","asd") ,
            Worker("213", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Andrey","Kirov","su","asd","Senior","asd","asd"),
            Worker("4234", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Anna","Babina","ru","asd","Devops","asd","asd"),
            Worker("463456", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Vasiliy","Ostovkin","bn","4","asd","Middle","asd"),
            Worker("a5436546sd", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Vladimir","Dilama","mi","asd","Devops","asd","asd"),
            Worker("3565346", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Victor","Tsoy","ru","asd","Middle","asd","asd"),
            Worker("34564356", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Nika","Rovenova","mi","asd","Middle","asd","asd"),
            Worker("346345", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Valeriy","Ivanov","ru","asd","Devops","asd","asd"),
            Worker("345435", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Dmitriy","Antonov","su","asd","Senior","asd","asd"),
            Worker("234234", "https://shapka-youtube.ru/wp-content/uploads/2021/02/prikolnaya-avatarka-dlya-patsanov.jpg", "Angelina","Kein","to","asd","Middle","asd","asd")
        )*/

    }
}