package com.example.therapist.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.therapist.data.model.ChatHistoryEntity
import com.example.therapist.data.model.TherapistAppDao
import com.example.therapist.data.model.UserInfoEntity

@Database(
    entities = [UserInfoEntity::class, ChatHistoryEntity::class],
    version = 1
)
abstract class TherapistAppDatabase: RoomDatabase() {
    abstract fun dao(): TherapistAppDao
}