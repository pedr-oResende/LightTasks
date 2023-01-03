package br.com.lighttasks.domain.usecase.user

import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LogoutUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(id: Long): Flow<Unit?> {
        PreferencesWrapper.getInstance()?.clearPreferences()
        return repository.logout(id)
    }
}