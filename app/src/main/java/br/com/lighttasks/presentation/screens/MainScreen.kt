package br.com.lighttasks.presentation.screens

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.lighttasks.commom.util.PreferencesKey
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.presentation.compose.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    val navHostController = rememberAnimatedNavController()
    val snackbarHost = remember { SnackbarHostState() }
    val isLoggedIn = PreferencesWrapper.instance?.getBoolean(PreferencesKey.IS_LOGGED_IN_KEY)
    val startDestination = if (true) Screens.Tasks.route else Screens.Login.route
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHost)
        }
    ) { paddingValues ->
        AnimatedNavHost(
            modifier = Modifier.padding(paddingValues = paddingValues),
            navController = navHostController,
            startDestination = startDestination,
            builder = {
                register(
                    onBackPressedDispatcher = onBackPressedDispatcher,
                    snackbarHost = snackbarHost
                )
                login(
                    navHostController = navHostController,
                    snackbarHost = snackbarHost
                )
                home(
                    navHostController = navHostController,
                    snackBarHost = snackbarHost
                )
                taskDetail(
                    navHostController = navHostController,
                    onBackPressedDispatcher = onBackPressedDispatcher
                )
                teamDetail(
                    navHostController = navHostController,
                    onBackPressedDispatcher = onBackPressedDispatcher
                )
            }
        )
    }
}