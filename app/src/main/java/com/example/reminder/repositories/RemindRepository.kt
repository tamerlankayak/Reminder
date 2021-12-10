package com.example.reminder.repositories

import androidx.lifecycle.LiveData
import com.example.reminder.dao.RemindDao
import com.example.reminder.entity.Remind

class RemindRepository(private val remindDao: RemindDao) {

    val readAllReminds: LiveData<List<Remind>> = remindDao.getAllReminds()

    suspend fun addRemind(remind: Remind) {
        remindDao.insertNewRemind(remind)
    }

    suspend fun getById(id: Long) :LiveData<Remind>{
       return  remindDao.getRemindById(id)
    }

}