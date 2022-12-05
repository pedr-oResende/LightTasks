package br.com.lighttasks.presentation.screens.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lighttasks.domain.model.User
import br.com.lighttasks.domain.usecase.user.RegisterUseCase
import br.com.lighttasks.domain.validator.ValidatePassword
import br.com.lighttasks.domain.validator.ValidateUsername
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.register.ui.RegisterActions
import br.com.lighttasks.presentation.screens.register.ui.RegisterUI
import br.com.lighttasks.presentation.screens.ui.CommonEvents
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val validateUsername: ValidateUsername,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    private val _registerState = MutableStateFlow<StateUI<Unit>>(StateUI.Idle())
    val registerState: StateFlow<StateUI<Unit>> = _registerState

    private val _registerUI = mutableStateOf(RegisterUI())
    val registerUI: State<RegisterUI> = _registerUI

    private val _eventFlow = MutableSharedFlow<CommonEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(events: RegisterActions) {
        when (events) {
            is RegisterActions.UsernameTextChanged -> {
                _registerUI.value = registerUI.value.copy(
                    username = events.text
                )
            }
            is RegisterActions.FullNameTextChanged -> {
                _registerUI.value = registerUI.value.copy(
                    fullName = events.text
                )
            }
            is RegisterActions.PasswordTextChanged -> {
                _registerUI.value = registerUI.value.copy(
                    password = events.text
                )
            }
            is RegisterActions.ShowPassword -> {
                _registerUI.value = registerUI.value.copy(
                    isPasswordVisible = !registerUI.value.isPasswordVisible
                )
            }
            is RegisterActions.ToggleRegister -> {
                if (validateUser())
                    register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            registerUseCase(getUser()).onStart {
                _registerState.emit(StateUI.Processing())
            }.catch {
                _registerState.emit(StateUI.Error(it.message.toString()))
            }.collect {
                _registerState.emit(StateUI.Processed(it))
            }
        }
    }

    suspend fun emitLoginEvents(action: CommonEvents) {
        _eventFlow.emit(action)
    }

    private fun validateUser(): Boolean {
        val usernameResult = validateUsername(username = registerUI.value.username)
        if (!usernameResult.successful) {
            throwErrorMessage(usernameResult.errorMessage)
            return false
        }
        val passwordResult = validatePassword(password = registerUI.value.password)
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

    private fun getUser() = User(
        id = null,
        username = _registerUI.value.username,
        fullName = _registerUI.value.fullName,
        password = _registerUI.value.password
    )

}