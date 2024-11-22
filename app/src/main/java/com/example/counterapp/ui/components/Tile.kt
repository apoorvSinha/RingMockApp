package com.example.yourapp.ui.components

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
import androidx.compose.ui.unit.dp

@Composable
fun Tile(index: Int) {
    val context = LocalContext.current
    val imageResource = when (index) {
        0 -> "camera_feed_1"
        1 -> "camera_feed_2"
        2 -> "camera_feed_3"
        3 -> "camera_feed_4"
        else -> "camera_feed_5"
    }

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
                painter = androidx.compose.ui.res.painterResource(id = resourceId),
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
