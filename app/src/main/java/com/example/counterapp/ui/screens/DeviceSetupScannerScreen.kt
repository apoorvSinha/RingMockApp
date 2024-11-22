package com.example.yourapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSetupScannerScreen(onBackPress: () -> Unit, deviceName: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            title = { Text("Set up $deviceName") },
            navigationIcon = {
                IconButton(onClick = onBackPress) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        // Content
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            // Instructions or other content can go here
            Text(
                "Scan the QR code on your $deviceName",
                modifier = Modifier.align(Alignment.TopCenter)
            )

            // Scanner area (black box for now)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp) // Adjust this value as needed
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp) // Space from the bottom of the screen
                    .background(Color.Black)
            )
        }
    }
}
