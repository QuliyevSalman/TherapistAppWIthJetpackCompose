package com.example.therapist.data.mapper

import com.example.therapist.data.model.ChatHistoryEntity
import com.example.therapist.domain.model.TherapistDomainModelChat

fun ChatHistoryEntity.toTherapistDomainModelChat(): TherapistDomainModelChat{
    return TherapistDomainModelChat(
        question,
        response
    )
}