package com.example.therapist.domain.usecases

import com.example.therapist.domain.model.TherapistDomainModelChat
import com.example.therapist.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllChatUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<TherapistDomainModelChat>>{
        return repository.getAllChat()
    }
}