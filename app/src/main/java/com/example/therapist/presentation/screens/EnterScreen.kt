package com.example.therapist.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.therapist.presentation.event.Events
import com.example.therapist.presentation.destination.Destination
import com.example.therapist.presentation.viewmodel.GetUserInfoViewModel
import com.example.therapist.presentation.viewmodel.TherapistViewModel
import com.example.therapist.ui.theme.MessageBackgroundColor

@Composable
fun EnterScreen(
    viewModel: TherapistViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {
    var apiKey by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    val linkApiKey = buildAnnotatedString {
        append("Do you have ")
        withLink(
            LinkAnnotation.Url(
                "https://aistudio.google.com/app/api-keys",
                TextLinkStyles(style = SpanStyle(color = Color.Blue))
            )
        ) {
            append("ApiKey?")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = {
                userName = it
            },
            placeholder = {
                Text(
                    text = "Enter Your Name"
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 12.dp
                )
        )
        OutlinedTextField(
            value = apiKey,
            onValueChange = {
                apiKey = it.trim().take(39)
            },
            placeholder = {
                Text(
                    text = "Enter Your Api Key"
                )
            },
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 12.dp
                )
        )
        Text(
            text = linkApiKey,
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MessageBackgroundColor
            ),
            onClick = {
                if (apiKey.isNotBlank() && userName.isNotBlank()) {
                    viewModel.controlEvent(Events.RegisterUser(
                        userName,
                        apiKey
                    ))
                    navController.navigate(Destination.CHAT_SCREEN.name){
                        popUpTo(Destination.ENTER_SCREEN.name){
                            inclusive = true
                        }
                    }
                }
            }
        ) {
            Text(
                "Start",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}