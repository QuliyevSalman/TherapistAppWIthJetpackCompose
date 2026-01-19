package com.example.therapist.presentation.mapper

import com.example.therapist.domain.model.TherapistDomainModelUserInfo
import com.example.therapist.presentation.model.UserInfoModel

fun UserInfoModel.toTherapistDomainModelUserInfo(): TherapistDomainModelUserInfo{
    return TherapistDomainModelUserInfo(
        userName,
        apiKey
    )
}