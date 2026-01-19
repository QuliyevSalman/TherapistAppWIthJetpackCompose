package com.example.therapist.data.mapper

import com.example.therapist.data.model.ChatHistoryEntity
import com.example.therapist.domain.model.TherapistDomainModelChat

fun TherapistDomainModelChat.toChatHistoryEntity(): ChatHistoryEntity{
    return ChatHistoryEntity(
        question,
        response
    )
}