package com.onsevenine.tabletenniscounter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.onsevenine.tabletenniscounter.AppViewModel
import com.onsevenine.tabletenniscounter.ui.Screen

@Composable
fun ResultScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier,
) {
    val state by appViewModel.state.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        state.gameResults.forEachIndexed { index, result ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Game ${index + 1}")
                Text(text = result.toString())
            }
        }

        Button(onClick = {
            appViewModel.updateScreen(Screen.Home.name)
            appViewModel.restartMatch()
            navController.popBackStack(Screen.Home.name, inclusive = false)
        }) {
            Text(text = "Home")
        }
    }
}