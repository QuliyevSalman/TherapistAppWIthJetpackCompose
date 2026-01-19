package com.example.therapist.data.mapper

import com.example.therapist.data.model.UserInfoEntity
import com.example.therapist.domain.model.TherapistDomainModelUserInfo

fun UserInfoEntity.toTherapistDomainModelUserInfo(): TherapistDomainModelUserInfo{
    return TherapistDomainModelUserInfo(
        userName,
        apiKey
    )
}