package com.onsevenine.tabletenniscounter.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.onsevenine.tabletenniscounter.AppViewModel

@Composable
fun PlayerCard(
    playerNumber: Int,
    appViewModel: AppViewModel,
    showResult: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by appViewModel.state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                // Score updated to +1
                appViewModel.updateScore(playerNumber, 1)
                if (appViewModel.checkGameOver(deuce = state.deuce)) {
                    if (appViewModel.checkMatchOver()) {
                        showResult()
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.whoseServe == playerNumber) {
                IconButton(onClick = { appViewModel.changeServe() }) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "")
//                    Text(text = "\uD83C\uDFD3")
                }
            } else {
                IconButton(onClick = {}) {}
            }
            Counter(score = if (playerNumber == 1) state.point1 else state.point2)
            PlayerId(
                name = if (playerNumber == 1) state.namePlayer1 else state.namePlayer2,
//                name = state.gameNumber.toString(),
                country = "\uD83C\uDDEE\uD83C\uDDF3",
            )
        }
    }
}