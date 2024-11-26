package com.example.counterapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeviceSetupProgressScreen(
    heading: String,
    onCompletion: () -> Unit
) {
    val progress = remember { mutableStateOf(0f) }
    val RingBlue = Color(0xFF03A9F4) // Replace with the exact Ring color if it's different

    LaunchedEffect(Unit) {
        for (i in 0..100) {
            progress.value = i / 100f
            kotlinx.coroutines.delay(100)
        }
    }

    // Launching animation
    LaunchedEffect(Unit) {
        for (i in 0..100) {
            progress.value = i / 100f
            kotlinx.coroutines.delay(100) // 100ms per increment, total of 10 seconds
        }
        onCompletion()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Heading
        Text(
            text = heading,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Progress Bar
        LinearProgressIndicator(
            progress = progress.value,
            color = RingBlue,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Percentage Text
        Text(
            text = "${(progress.value * 100).toInt()}%",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}