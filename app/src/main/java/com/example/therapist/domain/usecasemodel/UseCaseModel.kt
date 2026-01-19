package com.example.therapist.domain.usecasemodel

import com.example.therapist.domain.usecases.GetAllChatUseCase
import com.example.therapist.domain.usecases.GetUserInfoUseCase
import com.example.therapist.domain.usecases.UpsertChatUseCase
import com.example.therapist.domain.usecases.UpsertUserUseCase

data class UseCaseModel(
    val upsertUserUseCase: UpsertUserUseCase,
    val getUserInfoUseCase: GetUserInfoUseCase,
    val upsertChatUseCase: UpsertChatUseCase,
    val getAllChatUseCase: GetAllChatUseCase
)
