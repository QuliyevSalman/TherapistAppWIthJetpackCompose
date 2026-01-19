package com.example.therapist.presentation.model

import com.example.therapist.domain.usecasemodel.UseCaseModel

data class UserInfoModel(
    val userName: String,
    val apiKey: String
)
data class ChatHistoryModel(
    val question: String,
    val response: String
)
