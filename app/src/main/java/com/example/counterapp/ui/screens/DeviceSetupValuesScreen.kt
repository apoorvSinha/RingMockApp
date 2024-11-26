package com.example.counterapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSetupValuesScreen(
    onBackPress: () -> Unit,
    onContinueManually: () -> Unit,
    onConfirm: () -> Unit
) {
    var inputValue1 by remember { mutableStateOf("My Location") }
    var inputValue2 by remember { mutableStateOf("Cocoa Camera") }
    var checkboxStates by remember { mutableStateOf(List(5) { false }) }

    Column(modifier = Modifier.padding(vertical = 48.dp)) {
        // Fixed heading
        Text(
            "Do you want to use the saved values for outdoor device?",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp)
        )

        // Scrollable content
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Input boxes
                OutlinedTextField(
                    value = inputValue1,
                    onValueChange = { inputValue1 = it },
                    label = { Text("Location", fontSize = 18.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = inputValue2,
                    onValueChange = { inputValue2 = it },
                    label = { Text("Device Name", fontSize = 18.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                // Checkboxes
                checkboxStates.forEachIndexed { index, isChecked ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (index) {
                                0 -> "Removed the Protective film"
                                1 -> "Removed the face plate"
                                2 -> "Close to the doorbell"
                                3 -> "Light is spinning white"
                                else -> "Wifi/Bluetooth is turned on your phone"
                            },
                            modifier = Modifier.weight(1f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Row {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = isChecked,
                                    onClick = {
                                        checkboxStates = checkboxStates.toMutableList().also { it[index] = true }
                                    }
                                )
                                Text("Yes", Modifier.padding(start = 4.dp), fontSize = 16.sp)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = !isChecked,
                                    onClick = {
                                        checkboxStates = checkboxStates.toMutableList().also { it[index] = false }
                                    }
                                )
                                Text("No", Modifier.padding(start = 4.dp), fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        }

        // Additional button: Continue Setting Up Manually
        Button(
            onClick = onContinueManually,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(56.dp)
        ) {
            Text("Continue Setting Up Manually", fontSize = 18.sp)
        }

        // Fixed button at the bottom: Confirm
        Button(
            onClick = onConfirm,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(56.dp)
        ) {
            Text("Confirm", fontSize = 18.sp)
        }
    }
}
