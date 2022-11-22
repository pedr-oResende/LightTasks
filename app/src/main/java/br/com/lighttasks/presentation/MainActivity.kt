package br.com.lighttasks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.com.lighttasks.presentation.compose.theme.LightTasksTheme
import br.com.lighttasks.presentation.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LightTasksTheme {
                MainScreen(onBackPressedDispatcher = onBackPressedDispatcher)
            }
        }
    }
}
