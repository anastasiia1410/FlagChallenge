package com.example.flagchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flagchallenge.ui.finish.Finish
import com.example.flagchallenge.ui.greeting.Greeting
import com.example.flagchallenge.ui.quiz.Quiz
import com.example.flagchallenge.ui.quiz.QuizViewModel

@Composable
fun Navigation(viewModel: QuizViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.GreetingScreen.route) {
        composable(route = Screen.GreetingScreen.route) {
            Greeting(navController = navController)
        }
        composable(route = Screen.QuizScreen.route) {
            Quiz(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.FinishScreen.route + "/{rightAnswers}/{wrongAnswers}",
            arguments = listOf(
                navArgument("rightAnswers") {
                    type = NavType.IntType
                },
                navArgument("wrongAnswers") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            Finish(
                viewModel = viewModel,
                navController = navController,
                rightAnswers = entry.arguments?.getInt("rightAnswers"),
                wrongAnswers = entry.arguments?.getInt("wrongAnswers")
            )
        }
    }
}