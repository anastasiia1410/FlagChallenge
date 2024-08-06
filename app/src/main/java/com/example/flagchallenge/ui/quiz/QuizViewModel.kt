package com.example.flagchallenge.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.flagchallenge.data.network.NetworkRepository
import com.example.flagchallenge.ui.entity.GameMode
import com.example.flagchallenge.ui.entity.GameState
import com.example.flagchallenge.ui.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _gameState =
        MutableStateFlow(GameMode())
    val gameState: StateFlow<GameMode> get() = _gameState

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countryList = networkRepository.getCountries().shuffled()
                _gameState.emit(_gameState.value.copy(countryList = countryList))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun answerQuestion(isCorrect: Boolean) {
        viewModelScope.launch {
            if (isCorrect) {
                _gameState.emit(
                    _gameState.value.copy(
                        rightAnswers = _gameState.value.rightAnswers.plus(1)
                    )
                )
            } else {
                _gameState.emit(
                    _gameState.value.copy(
                        wrongAnswers = _gameState.value.wrongAnswers.plus(1)
                    )
                )
            }

            if (_gameState.value.currentRound < _gameState.value.rounds) {
                _gameState.emit(_gameState.value.copy(gameState = GameState.PROGRESS))
                _gameState.emit(
                    _gameState.value.copy(
                        currentRound = _gameState.value.currentRound + 1
                    )
                )
                getData()
            } else {
                _gameState.emit(_gameState.value.copy(gameState = GameState.FINISH))
            }
        }
    }

    fun navigateToFinishScreen(navController: NavController) {
        navController.navigate(
            Screen.FinishScreen.withArgs(
                _gameState.value.rightAnswers,
                _gameState.value.wrongAnswers
            )
        )
    }

    fun startNewGame() {
        viewModelScope.launch {
            _gameState.emit(GameMode())
            getData()
        }
    }
}

