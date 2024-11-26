import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.counterapp.ui.screens.DeviceSetupProgressScreen
import com.example.counterapp.ui.screens.DeviceSetupValuesScreen
import com.example.counterapp.ui.components.DrawerContent
import com.example.counterapp.ui.components.ImageTile
import com.example.counterapp.ui.components.Tile
import com.example.counterapp.ui.components.TopControlBar
import com.example.counterapp.ui.screens.DeviceSetupSuccessScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityControlPage() {
    val navController = rememberNavController()
    var isSetupJustCompleted: Boolean = false

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
                },
                onDeviceSelected = { deviceName ->
                    navController.navigate("deviceSetupValues/$deviceName")
                }
            )
        }

        composable(
            "deviceSetupValues/{deviceName}",
            arguments = listOf(navArgument("deviceName") { type = NavType.StringType })
        ) { backStackEntry ->
            val deviceName = remember { backStackEntry.arguments?.getString("deviceName") ?: "" }
            DeviceSetupValuesScreen(
                onBackPress = {
                    navController.popBackStack()
                },
                onConfirm = {
                    navController.navigate("deviceSetupProgress")
                },
                onContinueManually = {
                    navController.navigate("deviceSetupScanner/$deviceName")
                }

            )
        }

        // New Route for Progress Screen
        composable("deviceSetupProgress") {
            DeviceSetupProgressScreen(
                heading = "Ring device is trying to setup, Waiting for an existing Ring device to auto setup",
                onCompletion = {
                    navController.navigate("deviceSetupSuccess")
                }
            )
        }

        composable("deviceSetupSuccess") {
            DeviceSetupSuccessScreen(
                onFinish = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
    /*
    composable(
        "deviceSetupScanner/{deviceName}",
        arguments = listOf(navArgument("deviceName") { type = NavType.StringType })
    ) { backStackEntry ->
        val deviceName = remember { backStackEntry.arguments?.getString("deviceName") ?: "" }
        DeviceSetupScannerScreen(
            onBackPress = {
                navController.popBackStack()
            },
            deviceName = deviceName
        )
    }*/
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityControlContent(onSetupDeviceClick: () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val RingBlue = Color(0xFF03A9F4) // Replace with the exact Ring color if it's different

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

                    // Rectangular button with red outline
                    Button(
                        onClick = { /* Handle button click */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(66.dp)
                            .border(2.dp, Color.Red, RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                Icons.Default.Warning,
                                contentDescription = "Attention",
                                tint = Color.Red
                            )
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(2.dp) // Adjust spacing as needed
                            ) {
                                Text(
                                    "Attention: SOS not setup",
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp

                                )
                                Text(
                                    "Action Required",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        // neighbour icons
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            ImageTile(
                                imageName = "neighbours",
                                imageDescription = "neighbour",
                                ratioHeight = 32f,
                                ratioWidth = 10f
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Camera tiles
                        items(5) { index ->
                            Tile(index)
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        //Promotion tiles
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            ImageTile(
                                imageName = "promotions",
                                imageDescription = "Promotions",
                                ratioHeight = 1f,
                                ratioWidth = 1f
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        item{
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Setup device button
                        item {
                            Button(
                                onClick = onSetupDeviceClick,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .height(48.dp), // Standard height for buttons
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White) // White background
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center // Center the text horizontally
                                ) {
                                    Text(
                                        text = "Set Up a Device",
                                        color = RingBlue, // Use the Ring brand color
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }
                    }
                }
            }

            // Bottom 10% empty space
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
            )
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
