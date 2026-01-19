package com.example.therapist.domain.repository

import com.example.therapist.domain.model.TherapistDomainModelChat
import com.example.therapist.domain.model.TherapistDomainModelUserInfo
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun upsertUser(therapistDomainModelUserInfo: TherapistDomainModelUserInfo)
    fun getUserInfo(): Flow<List<TherapistDomainModelUserInfo>>

    suspend fun upsertChat(therapistDomainModelChat: TherapistDomainModelChat)
    fun getAllChat(): Flow<List<TherapistDomainModelChat>>
}