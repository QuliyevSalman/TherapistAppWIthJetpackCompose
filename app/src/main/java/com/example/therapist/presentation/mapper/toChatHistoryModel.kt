package com.example.therapist.presentation.mapper

import com.example.therapist.domain.model.TherapistDomainModelChat
import com.example.therapist.presentation.model.ChatHistoryModel

fun TherapistDomainModelChat.toChatHistoryModel(): ChatHistoryModel{
    return ChatHistoryModel(
        question,
        response
    )
}