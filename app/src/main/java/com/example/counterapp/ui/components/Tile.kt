package com.example.counterapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Tile(index: Int, isUpdating: Boolean) {
    val context = LocalContext.current
    val imageResource = when (index) {
        0 -> if (isUpdating) "camera_feed_1_updating" else "camera_feed_1"
        1 -> "camera_feed_2"
        2 -> "camera_feed_3"
        3 -> "camera_feed_4"
        else -> "camera_feed_5"
    }

    Log.d("Image resources", "Image resource: $imageResource")

    val resourceId = context.resources.getIdentifier(imageResource, "drawable", context.packageName)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "Camera feed $index",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "Camera $index",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}
