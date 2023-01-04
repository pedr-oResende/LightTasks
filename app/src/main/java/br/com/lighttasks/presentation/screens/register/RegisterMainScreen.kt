package br.com.lighttasks.presentation.screens.register

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import br.com.lighttasks.presentation.compose.components.CustomLargeButton
import br.com.lighttasks.presentation.compose.components.dialog.LoadingDialog
import br.com.lighttasks.presentation.compose.widgets.edit_text.CustomEditText
import br.com.lighttasks.presentation.compose.widgets.top_bar.TopBar
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.register.ui.RegisterEvents
import br.com.lighttasks.presentation.screens.ui.CommonEvents
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterMainScreen(
    snackbarHost: SnackbarHostState,
    onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModel: RegisterViewModel
) {
    val (showLoadingDialog, setShowLoadingDialog) = remember { mutableStateOf(false) }
    val registerState = viewModel.registerState.collectAsState().value
    RegisterScreen(
        onBackPressedDispatcher = onBackPressedDispatcher,
        showLoadingDialog = showLoadingDialog,
        viewModel = viewModel
    )
    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest { actions ->
            when (actions) {
                is CommonEvents.GenericError -> {
                    snackbarHost.showSnackbar(message = "Não foi possível realizar o cadastro")
                }
                is CommonEvents.InvalidFieldsError -> {
                    snackbarHost.showSnackbar(message = actions.message)
                }
            }
        }
    }
    when (registerState) {
        is StateUI.Error -> {
            LaunchedEffect(true) {
                setShowLoadingDialog(false)
                viewModel.emitLoginEvents(CommonEvents.GenericError)
            }
        }
        is StateUI.Idle -> Unit
        is StateUI.Processed -> {
            setShowLoadingDialog(false)
            onBackPressedDispatcher.onBackPressed()
        }
        is StateUI.Processing -> {
            setShowLoadingDialog(true)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    showLoadingDialog: Boolean,
    viewModel: RegisterViewModel,
) {
    val registerUI = viewModel.registerUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = "Cadastro",
                onBackPressed = { onBackPressedDispatcher.onBackPressed() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .padding(paddingValues = paddingValues)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingDialog(
                    showDialog = showLoadingDialog,
                    text = "Realizando cadastro"
                )
                CustomEditText(
                    value = registerUI.username,
                    onValueChange = {
                        viewModel.onEvent(RegisterEvents.UsernameTextChanged(it))
                    },
                    placeholder = "nome de usuário"
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomEditText(
                    value = registerUI.fullName,
                    onValueChange = {
                        viewModel.onEvent(RegisterEvents.FullNameTextChanged(it))
                    },
                    placeholder = "nome completo"
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomEditText(
                    value = registerUI.password,
                    onValueChange = {
                        viewModel.onEvent(RegisterEvents.PasswordTextChanged(it))
                    },
                    placeholder = "senha",
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onEvent(RegisterEvents.ShowPassword) }) {
                            Icon(
                                imageVector = if (registerUI.isPasswordVisible)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                contentDescription = null
                            )
                        }
                    },
                    visualTransformation = if (registerUI.isPasswordVisible)
                        null
                    else
                        PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(64.dp))
                CustomLargeButton(
                    text = "Cadastrar",
                    onClick = { viewModel.onEvent(RegisterEvents.ToggleRegister) }
                )
            }
        }
    }
}