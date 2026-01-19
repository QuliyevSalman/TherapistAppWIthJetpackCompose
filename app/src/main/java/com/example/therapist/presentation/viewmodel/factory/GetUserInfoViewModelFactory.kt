package com.example.therapist.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.therapist.domain.usecasemodel.UseCaseModel
import com.example.therapist.presentation.viewmodel.GetUserInfoViewModel

@Suppress("UNCHECKED_CAST")
class GetUserInfoViewModelFactory(
    private val useCaseModel: UseCaseModel
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetUserInfoViewModel(useCaseModel) as T
    }
}