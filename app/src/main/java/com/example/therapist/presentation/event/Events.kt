package com.example.therapist.presentation.event

sealed interface Events {
    data class RegisterUser(
        val userName: String,
        val apiKey: String
    ): Events
    data class SendQuestion(
        val question: String,
        val apiKey: String
    ): Events
}