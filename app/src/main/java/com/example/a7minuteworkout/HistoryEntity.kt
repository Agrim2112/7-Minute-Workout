package com.example.a7minuteworkout

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "HistoryTable")
data class HistoryEntity(
    @PrimaryKey
    val date: String
)

