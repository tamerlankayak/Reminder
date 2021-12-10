package com.example.reminder.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.reminder.database.RemindDatabase
import com.example.reminder.entity.Remind
import com.example.reminder.repositories.RemindRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemindViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Remind>>

    private val remindRepository: RemindRepository

    init {
        val remindDao = RemindDatabase.getDatabase(application).remindDao()
        remindRepository = RemindRepository(remindDao)
        readAllData = remindRepository.readAllReminds
    }

    fun addRemind(remind: Remind) {
        viewModelScope.launch(Dispatchers.IO) {
            remindRepository.addRemind(remind)
        }
    }

    suspend fun getRemindById(id: Long): LiveData<Remind> {
        return remindRepository.getById(id)
    }
}