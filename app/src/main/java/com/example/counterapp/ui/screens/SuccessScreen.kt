package com.example.counterapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DeviceSetupSuccessScreen(
    onFinish: () -> Unit
) {
    val RingBlue = Color(0xFF03A9F4) // Replace with the exact Ring color if it's different
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Setup Successful!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your Ring device is now set up and ready to protect you.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onFinish,
            colors = ButtonDefaults.buttonColors(
                containerColor = RingBlue,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
        ) {
            Text("Finish")
        }
    }
}
