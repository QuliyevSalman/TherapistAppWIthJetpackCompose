package com.example.therapist.presentation.mapper

import com.example.therapist.domain.model.TherapistDomainModelChat
import com.example.therapist.presentation.model.ChatHistoryModel

fun ChatHistoryModel.toTherapistDomainModelChat(): TherapistDomainModelChat{
    return TherapistDomainModelChat(
        question,
        response
    )
}