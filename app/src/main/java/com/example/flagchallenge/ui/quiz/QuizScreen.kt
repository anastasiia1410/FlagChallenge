package com.example.flagchallenge.ui.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.flagchallenge.R
import com.example.flagchallenge.ui.entity.GameMode
import com.example.flagchallenge.ui.entity.GameState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun Quiz(navController: NavController, viewModel: QuizViewModel) {
    val snackBarHostState = remember { SnackbarHostState() }
    val state by viewModel.gameState.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(id = R.string.step, state.currentRound, 5),
                            color = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 24.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.choose_answer),
                                color = Color.DarkGray,
                                fontSize = 24.sp,
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                            Flag(state)
                            AnswerVariants(
                                viewModel,
                                navController,
                                state,
                                snackBarHostState
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun Flag(state: GameMode) {
    state.countryList.firstOrNull()?.let { quizItem ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(350.dp, 250.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(BorderStroke(2.dp, Color.DarkGray))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .decoderFactory(SvgDecoder.Factory())
                    .data(quizItem.flagUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun AnswerVariants(
    viewModel: QuizViewModel,
    navController: NavController,
    state: GameMode,
    snackBarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    val correctAnswerMessage = stringResource(id = R.string.correct_answer)
    val incorrectAnswerMessage = stringResource(id = R.string.wrong_answer)


    state.countryList.forEach { country ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .border(BorderStroke(2.dp, Color.DarkGray))
                .padding(vertical = 16.dp)
                .clickable {
                    coroutineScope.launch(Dispatchers.Main) {
                        val isCorrect = country.isCorrect
                        val message =
                            if (isCorrect) correctAnswerMessage else incorrectAnswerMessage
                        withContext(Dispatchers.Main) {
                            val job = coroutineScope.launch {
                                snackBarHostState.showSnackbar(
                                    message = message,
                                    duration = SnackbarDuration.Short,
                                )
                            }
                            delay(1000)
                            job.cancel()
                        }
                        if (state.currentRound <= state.rounds) {
                            viewModel.answerQuestion(isCorrect)
                        }
                        viewModel.gameState.collect { gameState ->
                            if (gameState.gameState == GameState.FINISH) {
                                viewModel.navigateToFinishScreen(navController)
                            }
                        }
                    }
                }
        ) {
            Text(
                text = country.countryName,
                modifier = Modifier.padding(horizontal = 24.dp),
                fontSize = 24.sp,
                color = Color.DarkGray
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}


