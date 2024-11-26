package com.example.counterapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopControlBar(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Hamburger menu (20%)
        IconButton(
            onClick = onMenuClick,
            modifier = Modifier.weight(0.2f)
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Menu")
        }

        // Dropdown (60%)
        var expanded by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.weight(0.6f)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = "My Location",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Location 1") },
                        onClick = { expanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Location 2") },
                        onClick = { expanded = false }
                    )
                }
            }
        }

        // Alert icon (20%)
        IconButton(
            onClick = { /* Handle alert click */ },
            modifier = Modifier.weight(0.2f)
        ) {
            Icon(Icons.Default.Notifications, contentDescription = "Alerts")
        }
    }
}


@Composable
fun DrawerContent(
    onItemClick: (String) -> Unit,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val items = listOf(
        "Back", "Dashboard", "Neighbours", "Devices", "History", "Settings",
        "Ring Plan", "Setup a device", "Account settings", "Control centre", "What's new"
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.7f)
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {
        items.forEach { item ->
            Button(
                onClick = {
                    onItemClick(item)
                    scope.launch {
                        drawerState.close()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray), // Add border
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = item,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


