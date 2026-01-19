package com.example.therapist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapist.domain.usecasemodel.UseCaseModel
import com.example.therapist.presentation.mapper.toUserInfoModel
import com.example.therapist.presentation.model.UserInfoModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class GetUserInfoViewModel(
    private val useCaseModel: UseCaseModel
): ViewModel() {
    val userInfo: StateFlow<List<UserInfoModel>> = useCaseModel.getUserInfoUseCase().map {
        list ->
        list.map {
            domainModelUserInfo ->
            domainModelUserInfo.toUserInfoModel()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}