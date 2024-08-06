package com.example.flagchallenge.ui.entity

data class GameMode(
    val gameState: GameState = GameState.START,
    val countryList: List<Country> = emptyList(),
    val rounds: Int = 5,
    val currentRound: Int = 1,
    val rightAnswers: Int = 0,
    val wrongAnswers: Int = 0,
    val isCorrectAnswer : Boolean = false
)

enum class GameState{
    START, PROGRESS, FINISH
}