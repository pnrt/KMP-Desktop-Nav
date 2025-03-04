package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import java.awt.Toolkit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.res.painterResource


@Composable
fun NavigationMenu(navigateTo: (String) -> Unit, isVisible: Boolean, onHide: () -> Unit) {
    if (!isVisible) return

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val options = listOf("Home", "Settings", "Profile", "About")
    val filteredOptions = options.filter { it.contains(searchQuery.text, ignoreCase = true) }

    Column(
        modifier = Modifier.fillMaxHeight().width(200.dp).padding(16.dp).background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Search") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        filteredOptions.forEach { option ->
            Button(
                onClick = { navigateTo(option) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(text = option)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onHide) {
            Icon(Icons.Default.Close, contentDescription = "Hide Menu")
        }
    }
}

@Composable
fun AppNavigator() {
    var currentScreen by remember { mutableStateOf("Home") }
    var isMenuVisible by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxSize()) {
        if (isMenuVisible) {
            NavigationMenu(navigateTo = { selected -> currentScreen = selected }, isVisible = isMenuVisible, onHide = { isMenuVisible = false })
        }

        Column(modifier = Modifier.fillMaxHeight().weight(4f).padding(16.dp)) {
            if (!isMenuVisible) {
                IconButton(onClick = { isMenuVisible = true }) {
                    Icon(Icons.Default.Menu, contentDescription = "Show Menu")
                }
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when (currentScreen) {
                    "Home" -> HomeScreen()
                    "Settings" -> SettingsScreen()
                    "Profile" -> ProfileScreen()
                    "About" -> AboutScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Text("Welcome to Home Screen", modifier = Modifier.padding(16.dp))
}

@Composable
fun SettingsScreen() {
    Text("Settings Page", modifier = Modifier.padding(16.dp))
}

@Composable
fun ProfileScreen() {
    Text("Profile Page", modifier = Modifier.padding(16.dp))
}

@Composable
fun AboutScreen() {
    Text("About Page", modifier = Modifier.padding(16.dp))
}

