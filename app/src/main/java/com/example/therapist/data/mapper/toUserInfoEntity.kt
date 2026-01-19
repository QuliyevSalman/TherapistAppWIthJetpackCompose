package com.example.therapist.data.mapper

import com.example.therapist.data.model.UserInfoEntity
import com.example.therapist.domain.model.TherapistDomainModelUserInfo

fun TherapistDomainModelUserInfo.toUserInfoEntity(): UserInfoEntity{
    return UserInfoEntity(
        userName,
        apiKey
    )
}