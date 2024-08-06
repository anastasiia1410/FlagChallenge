package com.example.flagchallenge.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.flagchallenge.ui.navigation.Navigation
import com.example.flagchallenge.ui.quiz.QuizViewModel
import com.example.flagchallenge.ui.theme.FlagChallengeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
   private val viewModel by viewModel<QuizViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlagChallengeTheme {
                Navigation(viewModel)
            }
        }
    }
}



