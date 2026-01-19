package com.example.therapist.presentation.mapper

import com.example.therapist.domain.model.TherapistDomainModelUserInfo
import com.example.therapist.presentation.model.UserInfoModel

fun TherapistDomainModelUserInfo.toUserInfoModel(): UserInfoModel{
    return UserInfoModel(
        userName,
        apiKey
    )
}