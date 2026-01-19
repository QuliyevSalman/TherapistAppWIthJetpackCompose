package com.example.therapist.presentation.model

data class UserInfoModel(
    val userName: String,
    val apiKey: String
)
data class ChatHistoryModel(
    val question: String,
    val response: String
)
