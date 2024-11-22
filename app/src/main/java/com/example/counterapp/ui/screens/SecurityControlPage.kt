import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yourapp.ui.components.CircularButton
import com.example.yourapp.ui.components.DrawerContent
import com.example.yourapp.ui.components.Tile
import com.example.yourapp.ui.components.TopControlBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityControlPage() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            SecurityControlContent(
                onSetupDeviceClick = {
                    navController.navigate("deviceSetup")
                }
            )
        }
        composable("deviceSetup") {
            DeviceSetupScreen(
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityControlContent(onSetupDeviceClick: () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val drawerWidth = animateDpAsState(
        if (drawerState.isOpen) (LocalConfiguration.current.screenWidthDp * 0.7f).dp else 0.dp,
        label = "drawerWidth"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top white strip
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.05f)
                    .background(Color.White)
            )

            // Main content area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f)
                    .background(Color.White)
            ) {
                Column {
                    TopControlBar(
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )

                    // Circular buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CircularButton(text = "Disarm", onClick = { /* Handle Disarm */ })
                        CircularButton(text = "Home", onClick = { /* Handle Home */ })
                        CircularButton(text = "Away", onClick = { /* Handle Away */ })
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Cameras",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, // Set font size to 20sp
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Scrollable tiles
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        items(5) { index ->
                            Tile(index)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }

            // Bottom 10% empty space
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f))
        }

        // Custom drawer
        Box(
            modifier = Modifier
                .offset(x = -drawerWidth.value + drawerWidth.value)
                .width(drawerWidth.value)
                .fillMaxHeight()
                .background(Color.White)
        ) {
            DrawerContent(
                onItemClick = { item ->
                    when (item) {
                        "Setup a device" -> onSetupDeviceClick()
                        else -> println("Clicked: $item")
                    }
                    scope.launch {
                        drawerState.close()
                    }
                },
                scope = scope,
                drawerState = drawerState
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecurityControlPagePreview() {
    SecurityControlPage()
}
