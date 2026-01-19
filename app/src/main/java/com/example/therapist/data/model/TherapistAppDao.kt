package com.example.therapist.data.model

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TherapistAppDao {
    @Upsert
    suspend fun upsertUser(userInfoEntity: UserInfoEntity)
    @Query("SELECT * FROM UserInfoEntity")
    fun getUserInfo(): Flow<List<UserInfoEntity>>
    @Upsert
    suspend fun upsertChat(chatHistoryEntity: ChatHistoryEntity)
    @Query("SELECT * FROM ChatHistoryEntity")
    fun getAllChat(): Flow<List<ChatHistoryEntity>>
}