package com.onsevenine.tabletenniscounter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.onsevenine.tabletenniscounter.AppViewModel
import com.onsevenine.tabletenniscounter.ui.components.PlayerCard

@Composable
fun HomeScreen(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PlayerCard(
            playerNumber = 1,
            appViewModel = appViewModel,
            modifier = Modifier
                .background(Color.Red)
                .fillMaxHeight(0.5f)
        )
        PlayerCard(
            playerNumber = 2,
            appViewModel = appViewModel,
            modifier = Modifier
                .background(Color.Black)
                .fillMaxHeight(1f)
        )
    }
}