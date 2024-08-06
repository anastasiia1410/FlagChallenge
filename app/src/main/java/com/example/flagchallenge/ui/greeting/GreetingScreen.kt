package com.example.flagchallenge.ui.greeting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flagchallenge.R
import com.example.flagchallenge.ui.navigation.Screen

@Composable
fun Greeting(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.astronaut),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .padding(16.dp)
        )

        Text(
            text = stringResource(id = R.string.greeting_text),
            fontSize = 30.sp,
            color = Color.DarkGray,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = stringResource(id = R.string.ready),
            fontSize = 24.sp,
            color = Color.DarkGray,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(top = 40.dp)
        )

        Button(
            onClick = {
                navController.navigate(Screen.QuizScreen.route)
            },
            modifier = Modifier
                .padding(vertical = 64.dp)
                .size(120.dp, 48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.start),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}