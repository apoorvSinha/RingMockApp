package com.example.yourapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularButton(text: String, onClick: () -> Unit) {
    val buttonColor = if (text.equals("Disarm", ignoreCase = true)) {
        Color.LightGray
    } else if (text.equals("Away", ignoreCase = true))(
        Color.Gray
    )
    else {
        Color.Red
    }

    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = Modifier.size(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(text = text)
    }
}
