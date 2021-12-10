package com.example.reminder.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reminder.entity.Remind

@Dao
interface RemindDao {
    @Insert
    suspend fun insertNewRemind(vararg remind: Remind)

    @Query("SELECT * FROM Reminds ORDER BY ID DESC")
    fun getAllReminds(): LiveData<List<Remind>>

    @Query("SELECT * FROM Reminds WHERE id=:remindId")
    fun getRemindById(remindId: Long): LiveData<Remind>
}