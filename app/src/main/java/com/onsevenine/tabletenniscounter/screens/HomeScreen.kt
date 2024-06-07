package com.onsevenine.tabletenniscounter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.onsevenine.tabletenniscounter.AppViewModel
import com.onsevenine.tabletenniscounter.ui.Screen
import com.onsevenine.tabletenniscounter.ui.components.PlayerCard

@Composable
fun HomeScreen(
    appViewModel: AppViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PlayerCard(
            playerNumber = 1,
            appViewModel = appViewModel,
            showResult = {
                appViewModel.updateScreen(Screen.Result.name)
                navController.navigate(Screen.Result.name)
            },
            modifier = Modifier
                .background(Color.Red)
                .fillMaxHeight(0.5f)
        )
        PlayerCard(
            playerNumber = 2,
            appViewModel = appViewModel,
            showResult = {
                navController.navigate(Screen.Result.name)
            },
            modifier = Modifier
                .background(Color.Black)
                .fillMaxHeight(1f)
        )
    }
}