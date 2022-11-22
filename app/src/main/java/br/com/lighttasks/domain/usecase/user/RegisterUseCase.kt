package br.com.lighttasks.domain.usecase.user

import br.com.lighttasks.domain.model.User
import br.com.lighttasks.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(user: User): Flow<Unit?> {

        return repository.register(user)
    }
}