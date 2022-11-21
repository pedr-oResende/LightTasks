package br.com.lighttasks.domain.usecase.user

import br.com.lighttasks.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class DeleteUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(id: Long): Flow<Unit?> {
        return repository.deleteUser(id)
    }
}