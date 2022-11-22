package br.com.lighttasks.presentation.ui.screens

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
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
    val isLoggedIn = PreferencesWrapper.instance?.getBoolean(PreferencesKey.IS_LOGGED_IN_KEY)
    val startDestination = if (isLoggedIn == true) Screens.Tasks.route else Screens.Login.route

    AnimatedNavHost(
        navController = navHostController,
        startDestination = startDestination,
        builder = {
            register(
                navHostController = navHostController,
                onBackPressedDispatcher
            )
            login(
                navHostController = navHostController
            )
            tasks(
                navHostController = navHostController
            )
            taskDetail(
                navHostController = navHostController,
                onBackPressedDispatcher = onBackPressedDispatcher
            )
            teams(
                navHostController = navHostController
            )
            teamDetail(
                navHostController = navHostController,
                onBackPressedDispatcher = onBackPressedDispatcher
            )
            profile(
                navHostController = navHostController,
                onBackPressedDispatcher = onBackPressedDispatcher
            )
        }
    )
}