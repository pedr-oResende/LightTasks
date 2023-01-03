package br.com.lighttasks.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lighttasks.commom.util.PreferencesKey
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.User
import br.com.lighttasks.domain.usecase.user.LoginUseCase
import br.com.lighttasks.domain.validator.ValidatePassword
import br.com.lighttasks.domain.validator.ValidateUsername
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.login.ui.LoginEvents
import br.com.lighttasks.presentation.screens.ui.CommonEvents
import br.com.lighttasks.presentation.screens.login.ui.LoginUI
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val validateUsername: ValidateUsername,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    private val _loginState = MutableStateFlow<StateUI<BasicUser>>(StateUI.Idle())
    val loginState: StateFlow<StateUI<BasicUser>> = _loginState

    private val _loginUi = mutableStateOf(LoginUI())
    val loginUI: State<LoginUI> = _loginUi

    private val _eventFlow = MutableSharedFlow<CommonEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.ToggleLogin -> {
                if (validateUser())
                    doLogin()
            }
            is LoginEvents.PasswordTextChanged -> {
                _loginUi.value = loginUI.value.copy(
                    password = event.text
                )
            }
            is LoginEvents.UsernameTextChanged -> {
                _loginUi.value = loginUI.value.copy(
                    username = event.text
                )
            }
            is LoginEvents.ShowPassword -> {
                _loginUi.value = loginUI.value.copy(
                    isPasswordVisible = !loginUI.value.isPasswordVisible
                )
            }
        }
    }

    private fun doLogin() {
        viewModelScope.launch {
            loginUseCase(getUser()).onStart {
                _loginState.emit(StateUI.Processing())
            }.catch {
                _loginState.emit(StateUI.Error(it.message.toString()))
            }.collect {
                it?.let { basicUser ->
                    _loginState.emit(StateUI.Processed(it))
                    PreferencesWrapper.getInstance()?.apply {
                        putBoolean(PreferencesKey.IS_LOGGED_IN_KEY, true)
                        setUser(basicUser)
                    }
                }
            }
        }
    }

    private fun getUser() =
        _loginUi.value.run {
            User(
                id = null,
                username = username,
                fullName = null,
                password = password
            )
        }

    suspend fun emitLoginEvents(action: CommonEvents) {
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
                emitLoginEvents(CommonEvents.InvalidFieldsError(message))
        }
    }
}