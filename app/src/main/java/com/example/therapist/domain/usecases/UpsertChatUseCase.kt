package com.example.therapist.domain.usecases

import com.example.therapist.domain.model.TherapistDomainModelChat
import com.example.therapist.domain.repository.Repository

class UpsertChatUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(therapistDomainModelChat: TherapistDomainModelChat){
        repository.upsertChat(therapistDomainModelChat)
    }
}