package com.example.counterapp.ui.components

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageTile(imageName: String, imageDescription: String, ratioWidth: Float, ratioHeight: Float) {
    val context = LocalContext.current
    val imageResource = imageName

    val resourceId = context.resources.getIdentifier(imageResource, "drawable", context.packageName)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ratioHeight / ratioWidth)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = imageDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
