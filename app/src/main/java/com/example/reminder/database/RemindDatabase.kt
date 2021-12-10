package com.example.reminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reminder.dao.RemindDao
import com.example.reminder.entity.Remind
import com.example.reminder.database.RemindDatabase as RemindDatabase

@Database(entities = [Remind::class], version = 1, exportSchema = false)
abstract class RemindDatabase : RoomDatabase() {

    abstract fun remindDao(): RemindDao

    companion object {
        @Volatile
        private var INSTANCE: RemindDatabase? = null

        fun getDatabase(context: Context): RemindDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RemindDatabase::class.java,
                    "remind_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}