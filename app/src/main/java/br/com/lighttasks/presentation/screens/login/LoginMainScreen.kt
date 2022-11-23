package br.com.lighttasks.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.lighttasks.presentation.compose.components.CustomLargeButton
import br.com.lighttasks.presentation.compose.components.dialog.LoadingDialog
import br.com.lighttasks.presentation.compose.navigation.Screens
import br.com.lighttasks.presentation.compose.widgets.CustomEditText
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.login.ui.LoginActions
import br.com.lighttasks.presentation.screens.login.ui.LoginEvents
import br.com.lighttasks.presentation.screens.login.ui.LoginUI
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginMainScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel,
    snackbarHost: SnackbarHostState
) {
    val loginUI = viewModel.loginUI.value
    val loginResponse = viewModel.loginResponse.collectAsState().value
    val (showLoadingDialog, setShowLoadingDialog) = remember { mutableStateOf(false) }
    LoginScreen(
        loginUI = loginUI, 
        showLoadingDialog = showLoadingDialog, 
        viewModel = viewModel
    )
    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest { actions ->
            when (actions) {
                // TODO(receber a mensagem de erro pelo backend)
                is LoginEvents.GenericError -> {
                    snackbarHost.showSnackbar(message = "Não foi possível realizar o login")
                }
                is LoginEvents.InvalidFieldsError -> {
                    snackbarHost.showSnackbar(message = actions.message)
                }
            }
        }
    }
    when (loginResponse) {
        is StateUI.Error -> {
            LaunchedEffect(true) {
                setShowLoadingDialog(false)
                viewModel.emitLoginEvents(LoginEvents.GenericError)
            }
        }
        is StateUI.Idle -> Unit
        is StateUI.Processed -> {
            setShowLoadingDialog(false)
            navHostController.navigate(Screens.Tasks.route)
        }
        is StateUI.Processing -> {
            setShowLoadingDialog(true)
        }
    }
}

@Composable
fun LoginScreen(
    loginUI: LoginUI,
    showLoadingDialog: Boolean,
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingDialog(
            showDialog = showLoadingDialog,
            text = "Fazendo login",
            onDismissRequest = { }
        )
        CustomEditText(
            value = loginUI.username,
            onValueChange = {
                viewModel.onEvent(LoginActions.UsernameTextChanged(it))
            },
            placeholder = "username"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomEditText(
            value = loginUI.password,
            onValueChange = {
                viewModel.onEvent(LoginActions.PasswordTextChanged(it))
            },
            placeholder = "password",
            trailingIcon = {
                IconButton(onClick = { viewModel.onEvent(LoginActions.ShowPassword) }) {
                    Icon(
                        imageVector = if (loginUI.isPasswordVisible)
                            Icons.Default.VisibilityOff
                        else
                            Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (loginUI.isPasswordVisible)
                null
            else
                PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(64.dp))
        CustomLargeButton(
            text = "Login",
            onClick = { viewModel.onEvent(LoginActions.ToggleLogin) }
        )
    }
}