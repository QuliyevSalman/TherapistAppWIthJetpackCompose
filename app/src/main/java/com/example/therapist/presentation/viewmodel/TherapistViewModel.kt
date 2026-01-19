package com.example.therapist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapist.domain.usecasemodel.UseCaseModel
import com.example.therapist.presentation.event.Events
import com.example.therapist.presentation.mapper.toTherapistDomainModelChat
import com.example.therapist.presentation.mapper.toTherapistDomainModelUserInfo
import com.example.therapist.presentation.model.ChatHistoryModel
import com.example.therapist.presentation.model.UserInfoModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class TherapistViewModel(
    private val useCaseModel: UseCaseModel
) : ViewModel() {

    fun controlEvent(events: Events) {
        when (events) {
            is Events.RegisterUser -> registerUser(events)
            is Events.SendQuestion -> sendQuestion(events)
        }
    }

    private fun registerUser(events: Events.RegisterUser) {
        viewModelScope.launch {
            useCaseModel.upsertUserUseCase(
                UserInfoModel(events.userName, events.apiKey).toTherapistDomainModelUserInfo()
            )
        }
    }

    private fun sendQuestion(events: Events.SendQuestion) {
        viewModelScope.launch {

            try {
                val generativeModel = GenerativeModel(modelName = "gemini-pro", apiKey = events.apiKey)

                val response = generativeModel.startChat().sendMessage(events.question)
                val responseText = response.text.toString()

                useCaseModel.upsertChatUseCase(
                    ChatHistoryModel(events.question, responseText).toTherapistDomainModelChat()
                )

            } catch (e: Exception) {
                useCaseModel.upsertChatUseCase(
                    ChatHistoryModel(events.question, "Hata: ${e.message}").toTherapistDomainModelChat()
                )
            }
        }
    }
}