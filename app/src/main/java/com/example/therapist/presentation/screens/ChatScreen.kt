package com.example.therapist.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.therapist.presentation.event.Events
import com.example.therapist.presentation.viewmodel.ChatHistoryViewModel
import com.example.therapist.presentation.viewmodel.GetUserInfoViewModel
import com.example.therapist.presentation.viewmodel.TherapistViewModel
import com.example.therapist.ui.theme.MessageBackgroundColor

@Composable
fun ChatScreen(
    getUserInfoViewModel: GetUserInfoViewModel,
    therapistViewModel: TherapistViewModel,
    viewModel: ChatHistoryViewModel,
    paddingValues: PaddingValues
) {
    val apiKey = getUserInfoViewModel.userInfo.collectAsState()
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        MessagesColumn(
            viewModel,
            modifier = Modifier.weight(1f)
        )
        MessageRow {
            therapistViewModel.controlEvent(
                Events.SendQuestion(
                    it,
                    apiKey.value.firstOrNull()?.apiKey ?: "Api Key not found"
                )
            )
        }
    }
}

@Composable
fun MessagesColumn(
    viewModel: ChatHistoryViewModel,
    modifier: Modifier
) {
    val chatHistory = viewModel.chatHistory.collectAsState()
    LazyColumn(
        modifier,
        reverseLayout = true
    ) {
        items(chatHistory.value.reversed()) { list ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        end = 12.dp,
                        start = 60.dp
                    ),
                horizontalArrangement = Arrangement.End,
            ) {
                Box(
                    modifier = Modifier
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(bottomStart = 20.dp))
                        .background(MessageBackgroundColor)
                        .padding(12.dp)
                ) {
                    Text(
                        text = list.question,
                        color = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 12.dp,
                        end = 60.dp
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(topEnd = 20.dp))
                        .background(MessageBackgroundColor)
                        .padding(12.dp)
                ) {
                    SelectionContainer {
                        Text(
                            text = list.response,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun MessageRow(
    onMessageSend: (String) -> Unit
) {
    var message by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = {
                message = it
            },
            maxLines = 4,
            shape = RoundedCornerShape(12.dp)
        )
        IconButton(
            onClick = {
                if (message.isNotBlank()) {
                    onMessageSend(message)
                    message = ""
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = ""
            )
        }
    }
}