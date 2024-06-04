package com.onsevenine.tabletenniscounter

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun updateScreen(screen: String) {
        _state.update { currentState ->
            currentState.copy(
                screen = screen
            )
        }
    }

    fun startMatch() {
        restartMatch()

        _state.update { currentState ->
            currentState.copy(
                matchStarted = true,
            )
        }
    }

    fun restartMatch() {
        _state.update { currentState ->
            currentState.copy(
                noOfGames = 1,
                gameNumber = 1,
                whoseServe = 1,
                point1 = 0,
                point2 = 0,
                gamePoint = 11,
                deuce = false
            )
        }
    }

    fun updateMatchPreferences(
        noOfGames: Int,
        gamePoint: Int,
        firstServe: Int,
        deuce: Boolean,
    ) {
        _state.update { currentState ->
            currentState.copy(
                noOfGames = noOfGames,
                gamePoint = gamePoint,
                whoseServe = firstServe,
                deuce = deuce,
            )
        }
    }

    fun restartGame() {
        _state.update { currentState ->
            currentState.copy(
                point1 = 0,
                point2 = 0,
            )
        }
    }

    fun changeServe() {
        _state.update { currentState ->
            currentState.copy(
                whoseServe = if (currentState.whoseServe == 1) 2 else 1
            )
        }
    }

    fun updateScore(player: Int, updateValue: Int) {
        if (player == 1) {
            _state.update { currentState ->
                currentState.copy(
                    point1 = currentState.point1 + updateValue
                )
            }
        } else {
            _state.update { currentState ->
                currentState.copy(
                    point2 = currentState.point2 + updateValue
                )
            }
        }

        if ((_state.value.point1 + _state.value.point2) % 2 == 0) {
            _state.update { currentState ->
                currentState.copy(
                    whoseServe = if (currentState.whoseServe == 1) 2 else 1
                )
            }
        }
    }

    fun checkGameOver(deuce: Boolean): Boolean {
        val point1 = _state.value.point1
        val point2 = _state.value.point2
        val gamePoint = _state.value.gamePoint

        if (deuce) {
            if (
                point1 >= gamePoint && (point1 - point2) == 2
                || point2 >= gamePoint && (point2 - point1) == 2
            ) {
                restartGame()
                return true
            }

            return false
        }

        if (point1 == gamePoint || point2 == gamePoint) {
            restartGame()
            return true
        }

        return false
    }

    fun checkMatchOver(): Boolean {
        val noOfGames = _state.value.noOfGames
        val gameNumber = _state.value.gameNumber

        return gameNumber == noOfGames
    }
}