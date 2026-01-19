package com.example.therapist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.therapist.app.TherapistApplication
import com.example.therapist.data.repositoryImpl.RepositoryImpl
import com.example.therapist.domain.usecasemodel.UseCaseModel
import com.example.therapist.domain.usecases.GetAllChatUseCase
import com.example.therapist.domain.usecases.GetUserInfoUseCase
import com.example.therapist.domain.usecases.UpsertChatUseCase
import com.example.therapist.domain.usecases.UpsertUserUseCase
import com.example.therapist.presentation.destination.Destination
import com.example.therapist.presentation.screens.ChatScreen
import com.example.therapist.presentation.screens.EnterScreen
import com.example.therapist.presentation.viewmodel.ChatHistoryViewModel
import com.example.therapist.presentation.viewmodel.GetUserInfoViewModel
import com.example.therapist.presentation.viewmodel.TherapistViewModel
import com.example.therapist.presentation.viewmodel.factory.ChatHistoryViewModelFactory
import com.example.therapist.presentation.viewmodel.factory.GetUserInfoViewModelFactory
import com.example.therapist.presentation.viewmodel.factory.TherapistViewModelFactory
import com.example.therapist.ui.theme.TherapistTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        (application as TherapistApplication).db
    }
    private val repository by lazy{
        RepositoryImpl(db.dao())
    }
    private val upsertUserUseCase by lazy {
        UpsertUserUseCase(repository)
    }
    private val getUserInfoUseCase by lazy {
        GetUserInfoUseCase(repository)
    }
    private val upsertChatUseCase by lazy {
        UpsertChatUseCase(repository)
    }
    private val getAllChatUseCase by lazy {
        GetAllChatUseCase(repository)
    }
    private val useCaseModel by lazy {
        UseCaseModel(
            upsertUserUseCase,
            getUserInfoUseCase,
            upsertChatUseCase,
            getAllChatUseCase
        )
    }
    private val viewModel: TherapistViewModel by lazy {
        val factory = TherapistViewModelFactory(useCaseModel)
        ViewModelProvider(this, factory)[TherapistViewModel::class.java]
    }
    private val chatHistoryViewModel: ChatHistoryViewModel by lazy {
        val factory = ChatHistoryViewModelFactory(useCaseModel)
        ViewModelProvider(this, factory)[ChatHistoryViewModel::class.java]
    }
    private val getUserInfoViewModel: GetUserInfoViewModel by lazy {
        val factory = GetUserInfoViewModelFactory(useCaseModel)
        ViewModelProvider(this, factory)[GetUserInfoViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TherapistTheme {
                val navController = rememberNavController()
                val userInfo = getUserInfoViewModel.userInfo.collectAsState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp, horizontal = 12.dp)
                        ) {
                            Text(
                                "Test ChatBot",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF252525)
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController, startDestination = if (userInfo.value.isNotEmpty()){
                            Destination.ENTER_SCREEN.name
                        }else{
                            Destination.CHAT_SCREEN.name
                        }
                    ) {
                        composable(route = Destination.ENTER_SCREEN.name) {
                            EnterScreen(
                                viewModel,
                                navController,
                                innerPadding
                            )
                        }
                        composable(route = Destination.CHAT_SCREEN.name) {
                            ChatScreen(
                                getUserInfoViewModel,
                                viewModel,
                                chatHistoryViewModel,
                                innerPadding
                            )
                        }
                    }
                }
            }
        }
    }
}