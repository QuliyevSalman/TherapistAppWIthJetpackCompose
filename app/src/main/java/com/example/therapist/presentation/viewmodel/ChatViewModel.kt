package com.example.therapist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapist.domain.usecasemodel.UseCaseModel
import com.example.therapist.presentation.mapper.toChatHistoryModel
import com.example.therapist.presentation.model.ChatHistoryModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChatHistoryViewModel(
    private val useCaseModel: UseCaseModel
): ViewModel() {

    val chatHistory: StateFlow<List<ChatHistoryModel>> = useCaseModel.getAllChatUseCase().map {
        list ->
        list.map {
            domainModelChat ->
            domainModelChat.toChatHistoryModel()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}