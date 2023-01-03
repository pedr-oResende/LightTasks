package br.com.lighttasks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
