package com.example.therapist.domain.usecases

import com.example.therapist.domain.model.TherapistDomainModelUserInfo
import com.example.therapist.domain.repository.Repository

class UpsertUserUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(therapistDomainModelUserInfo: TherapistDomainModelUserInfo){
        repository.upsertUser(therapistDomainModelUserInfo)
    }
}