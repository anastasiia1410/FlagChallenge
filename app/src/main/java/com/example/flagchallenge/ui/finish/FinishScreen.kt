package com.example.flagchallenge.ui.finish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flagchallenge.R
import com.example.flagchallenge.ui.navigation.Screen
import com.example.flagchallenge.ui.quiz.QuizViewModel

@Composable
fun Finish(
    viewModel: QuizViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    rightAnswers: Int?,
    wrongAnswers: Int?,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.results),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (rightAnswers != null && wrongAnswers != null) {
            Text(
                text = stringResource(id = R.string.right_count, rightAnswers),
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.wrong_count, wrongAnswers),
                fontSize = 20.sp,
                color = Color.DarkGray
            )
        } else {
            Text(
                text = stringResource(id = R.string.error),
                fontSize = 20.sp,
                color = Color.DarkGray
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                viewModel.startNewGame()
                navController.navigate(Screen.QuizScreen.route) {
                    popUpTo(Screen.QuizScreen.route) { inclusive = true }
                }
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 64.dp)
                .size(120.dp)
        ) {
            Text(
                text = stringResource(id = R.string.start),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}