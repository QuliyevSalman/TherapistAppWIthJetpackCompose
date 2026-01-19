package com.example.therapist.domain.model


data class TherapistDomainModelUserInfo(
    val userName: String,
    val apiKey: String
)
data class TherapistDomainModelChat(
    val question: String,
    val response: String
)
