package br.com.lighttasks.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.User
import br.com.lighttasks.domain.usecase.user.LoginUseCase
import br.com.lighttasks.domain.validator.ValidatePassword
import br.com.lighttasks.domain.validator.ValidateUsername
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.login.ui.LoginActions
import br.com.lighttasks.presentation.screens.login.ui.LoginEvents
import br.com.lighttasks.presentation.screens.login.ui.LoginUI
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val validateUsername: ValidateUsername,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    private val _loginResponse = MutableStateFlow<StateUI<BasicUser>>(StateUI.Idle())
    val loginResponse: StateFlow<StateUI<BasicUser>> = _loginResponse

    private val _loginUi = mutableStateOf(LoginUI())
    val loginUI: State<LoginUI> = _loginUi

    private val _eventFlow = MutableSharedFlow<LoginEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginActions) {
        when (event) {
            LoginActions.ToggleLogin -> {
                if (validateUser())
                    doLogin()
            }
            is LoginActions.PasswordTextChanged -> {
                _loginUi.value = loginUI.value.copy(
                    password = event.text
                )
            }
            is LoginActions.UsernameTextChanged -> {
                _loginUi.value = loginUI.value.copy(
                    username = event.text
                )
            }
            is LoginActions.ShowPassword -> {
                _loginUi.value = loginUI.value.copy(
                    isPasswordVisible = !loginUI.value.isPasswordVisible
                )
            }
        }
    }

    private fun doLogin() {
        viewModelScope.launch {
            loginUseCase(getUser()).onStart {
                _loginResponse.emit(StateUI.Processing())
            }.catch {
                _loginResponse.emit(StateUI.Error(it.message.toString()))
            }.collect {
                it?.let { basicUser ->
                    _loginResponse.emit(StateUI.Processed(it))
                    PreferencesWrapper.instance?.setUser(basicUser)
                }
            }
        }
    }

    private fun getUser() =
        _loginUi.value.run {
            User(
                id = null,
                username = username,
                password = password
            )
        }

    suspend fun emitLoginEvents(action: LoginEvents) {
        _eventFlow.emit(action)
    }

    private fun validateUser(): Boolean {
        val usernameResult = validateUsername(username = loginUI.value.username)
        if (!usernameResult.successful) {
            throwErrorMessage(usernameResult.errorMessage)
            return false
        }
        val passwordResult = validatePassword(password = loginUI.value.password)
        if (!passwordResult.successful) {
            throwErrorMessage(passwordResult.errorMessage)
            return false
        }
        return true
    }

    private fun throwErrorMessage(message: String?) {
        viewModelScope.launch {
            if (message != null)
                emitLoginEvents(LoginEvents.InvalidFieldsError(message))
        }
    }
}