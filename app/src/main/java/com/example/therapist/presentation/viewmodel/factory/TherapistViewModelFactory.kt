package com.example.therapist.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.therapist.domain.usecasemodel.UseCaseModel
import com.example.therapist.presentation.viewmodel.TherapistViewModel

@Suppress("UNCHECKED_CAST")
class TherapistViewModelFactory(
    private val useCaseModel: UseCaseModel
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TherapistViewModel(useCaseModel) as T
    }
}