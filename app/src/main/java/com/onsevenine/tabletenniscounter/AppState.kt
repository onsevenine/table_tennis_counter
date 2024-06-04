package com.onsevenine.tabletenniscounter

import com.onsevenine.tabletenniscounter.ui.Screen

data class AppState(
    val screen: String = Screen.Home.name,
    val matchStarted: Boolean = false,
    val noOfGames: Int = 1,
    val gameNumber: Int = 1,
    val whoseServe: Int = 1,
    val namePlayer1: String = "Player 1",
    val namePlayer2: String = "Player 2",
    val point1: Int = 0,
    val point2: Int = 0,
    val gamePoint: Int = 11,
    val deuce: Boolean = false,
    val matchOver: Boolean = false,
)