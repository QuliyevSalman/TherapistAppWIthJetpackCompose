package com.example.therapist.data.repositoryImpl

import com.example.therapist.data.mapper.toChatHistoryEntity
import com.example.therapist.data.mapper.toTherapistDomainModelChat
import com.example.therapist.data.mapper.toTherapistDomainModelUserInfo
import com.example.therapist.data.mapper.toUserInfoEntity
import com.example.therapist.data.model.TherapistAppDao
import com.example.therapist.domain.model.TherapistDomainModelChat
import com.example.therapist.domain.model.TherapistDomainModelUserInfo
import com.example.therapist.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val dao: TherapistAppDao
): Repository {
    override suspend fun upsertUser(therapistDomainModelUserInfo: TherapistDomainModelUserInfo) {
        dao.upsertUser(therapistDomainModelUserInfo.toUserInfoEntity())
    }

    override fun getUserInfo(): Flow<List<TherapistDomainModelUserInfo>> {
        return dao.getUserInfo().map {
            list ->
            list.map {
                userInfoEntity ->
                userInfoEntity.toTherapistDomainModelUserInfo()
            }
        }
    }

    override suspend fun upsertChat(therapistDomainModelChat: TherapistDomainModelChat) {
        dao.upsertChat(therapistDomainModelChat.toChatHistoryEntity())
    }

    override fun getAllChat(): Flow<List<TherapistDomainModelChat>> {
        return dao.getAllChat().map {
            list ->
            list.map {
                chatHistoryEntity ->
                chatHistoryEntity.toTherapistDomainModelChat()
            }
        }
    }
}