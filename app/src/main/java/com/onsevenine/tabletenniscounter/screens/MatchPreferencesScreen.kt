package com.onsevenine.tabletenniscounter.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onsevenine.tabletenniscounter.AppViewModel

data class Options(
    var noOfGames: Int,
    var scoreToWin: Int,
    var firstServe: Int,
    var deuce: Boolean,
)

@Composable
fun MatchPreferencesScreen(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel,
) {
    val state by appViewModel.state.collectAsState()

    var preferencesState by remember {
        mutableStateOf(
            Options(
                noOfGames = state.noOfGames,
                scoreToWin = state.gamePoint,
                firstServe = state.whoseServe,
                deuce = state.deuce,
            )
        )
    }

    BackHandler {
        onClickBack()
    }

    Column(
        modifier = modifier,
    ) {
        Text(
            text = "No of games:",
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                preferencesState.noOfGames == 1,
                onClick = { preferencesState = preferencesState.copy(noOfGames = 1) }
            )
            Text(text = "1")
            RadioButton(
                preferencesState.noOfGames == 3,
                onClick = { preferencesState = preferencesState.copy(noOfGames = 3) }
            )
            Text(text = "3")
            RadioButton(
                preferencesState.noOfGames == 5,
                onClick = { preferencesState = preferencesState.copy(noOfGames = 5) }
            )
            Text(text = "5")
            RadioButton(
                preferencesState.noOfGames == 7,
                onClick = { preferencesState = preferencesState.copy(noOfGames = 7) }
            )
            Text(text = "7")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Score to win:",
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                preferencesState.scoreToWin == 11,
                onClick = { preferencesState = preferencesState.copy(scoreToWin = 11) }
            )
            Text(text = "11")
            RadioButton(
                preferencesState.scoreToWin == 21,
                onClick = { preferencesState = preferencesState.copy(scoreToWin = 21) }
            )
            Text(text = "21")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "First Serve:",
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                preferencesState.firstServe == 1,
                onClick = { preferencesState = preferencesState.copy(firstServe = 1) }
            )
            Text(text = state.namePlayer1)
            RadioButton(
                preferencesState.firstServe == 2,
                onClick = { preferencesState = preferencesState.copy(firstServe = 2) }
            )
            Text(text = state.namePlayer2)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Deuce:",
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                preferencesState.deuce,
                onClick = { preferencesState = preferencesState.copy(deuce = true) })
            Text(text = "Yes")
            RadioButton(
                !preferencesState.deuce,
                onClick = { preferencesState = preferencesState.copy(deuce = false) })
            Text(text = "No")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onClickBack() },
                modifier = Modifier.weight(0.4f)
            ) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                onClick = {
                    appViewModel.restartMatch()

                    appViewModel.updateMatchPreferences(
                        noOfGames = preferencesState.noOfGames,
                        gamePoint = preferencesState.scoreToWin,
                        firstServe = preferencesState.firstServe,
                        deuce = preferencesState.deuce,
                    )

                    onClickBack()
                },
                modifier = Modifier.weight(0.4f)
            ) {
                Text(text = "Start Match")
            }
        }
    }
}