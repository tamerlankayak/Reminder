package com.example.reminder.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reminds")
data class Remind(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "Title") val title: String?,
    @ColumnInfo(name = "Subject") val subject: String?
)