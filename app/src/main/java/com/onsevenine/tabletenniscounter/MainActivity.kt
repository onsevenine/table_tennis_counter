package com.onsevenine.tabletenniscounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onsevenine.tabletenniscounter.screens.HomeScreen
import com.onsevenine.tabletenniscounter.screens.MatchPreferencesScreen
import com.onsevenine.tabletenniscounter.ui.Screen
import com.onsevenine.tabletenniscounter.ui.theme.TableTennisCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val windowsInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowsInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

//        windowsInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TableTennisCounterTheme {
                TableTennisCounterApp(navController)
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableTennisCounterApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(),
) {
    val state by appViewModel.state.collectAsState()

    var isMenuVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (state.screen == Screen.MatchPreferences.name) {
                TopAppBar(title = {
                    Text(
                        text = "Match Preferences",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                })
            }
        },
        floatingActionButton = {
            if (state.screen == Screen.Home.name) {
                Column {
                    AnimatedVisibility(visible = isMenuVisible) {
                        Column {
                            IconButton(
                                onClick = {
                                    appViewModel.startMatch()
                                    isMenuVisible = false
                                },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                                    .background(Color.White)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow, contentDescription = "",
                                    tint = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            IconButton(
                                onClick = {
                                    isMenuVisible = false
                                    navController.navigate(Screen.MatchPreferences.name)
                                    appViewModel.updateScreen(Screen.MatchPreferences.name)
                                },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                                    .background(Color.White)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    IconButton(
                        onClick = {
                            isMenuVisible = !isMenuVisible
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.name) {
                HomeScreen(appViewModel = appViewModel)
            }

            composable(Screen.MatchPreferences.name) {
                MatchPreferencesScreen(
                    appViewModel = appViewModel,
                    onClickBack = {
                        appViewModel.updateScreen(Screen.Home.name)
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}