import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSetupScreen(onBackPress: () -> Unit) {
    Column {
        TopAppBar(
            title = { Text("Setup a Device") },
            navigationIcon = {
                IconButton(onClick = onBackPress) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        LazyColumn {
            items(getDeviceList()) { device ->
                ListItem(
                    headlineContent = { Text(device.name) },
                    supportingContent = { Text(device.description) },
                )
            }
        }
    }
}

data class Device(val name: String, val description: String)

fun getDeviceList(): List<Device> {
    return listOf(
        Device("Doorbells", "Indoor/Outdoor Doorbell"),
        Device("Security Camera", "Indoor/Outdoor security camera"),
        Device("Chime", "Indoor Chime"),
        Device("Security", "Alarm device"),
        Device("Pet Tag", "Safety for your pets"),
        Device("Door Sensor", "Detect door openings"),
        Device("Motion Sensor", "Detect movement in a room"),
        Device("Smart Lock", "Control access to your home"),
        Device("Smart Light", "Control access to your Lights"),
    )
}
