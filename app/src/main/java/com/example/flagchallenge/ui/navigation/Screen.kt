package com.example.flagchallenge.ui.navigation

sealed class Screen (val route : String) {
     object GreetingScreen : Screen("greeting_screen")
     object QuizScreen : Screen("quiz_screen")
     object FinishScreen : Screen("finish_screen")

    fun withArgs(rightAnswers: Int, wrongAnswers: Int): String {
        return buildString {
            append(route)
            append("/$rightAnswers")
            append("/$wrongAnswers")
        }
    }
}