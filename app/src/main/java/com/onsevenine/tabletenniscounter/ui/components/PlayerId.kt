package com.onsevenine.tabletenniscounter.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayerId(name: String, country: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = country)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
    }
}