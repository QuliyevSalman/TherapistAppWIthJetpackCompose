package com.example.therapist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class UserInfoEntity(
    val userName: String,
    val apiKey: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
@Entity
data class ChatHistoryEntity(
    val question: String,
    val response: String,
    val who: String = "User",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
