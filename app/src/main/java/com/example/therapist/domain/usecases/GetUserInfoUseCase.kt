package com.example.therapist.domain.usecases

import com.example.therapist.domain.model.TherapistDomainModelUserInfo
import com.example.therapist.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<TherapistDomainModelUserInfo>>{
        return repository.getUserInfo()
    }
}