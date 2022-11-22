package br.com.lighttasks.presentation.ui.screens

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import br.com.lighttasks.commom.util.PreferencesKey
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.presentation.ui.compose.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    val navHostController = rememberAnimatedNavController()
    val isLoggedIn = PreferencesWrapper.instance?.getString(PreferencesKey.IS_LOGGED_IN_KEY) != null
    val startDestination = if (isLoggedIn) Screens.Tasks.route else Screens.Register.route

    AnimatedNavHost(
        navController = navHostController,
        startDestination = startDestination,
        builder = {
            register(
                navHostController = navHostController
            )
            login(
                navHostController = navHostController
            )
            tasks(
                navHostController = navHostController
            )
            teams(
                navHostController = navHostController
            )
            profile(
                navHostController = navHostController
            )
        }
    )
}