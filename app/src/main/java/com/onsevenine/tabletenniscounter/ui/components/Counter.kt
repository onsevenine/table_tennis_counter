package com.onsevenine.tabletenniscounter.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Counter(score: Int) {
    Text(
        text = score.toString(),
        fontSize = MaterialTheme.typography.displayLarge.fontSize
    )
}