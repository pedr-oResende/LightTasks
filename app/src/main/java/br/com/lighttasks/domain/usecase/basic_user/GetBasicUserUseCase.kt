package br.com.lighttasks.domain.usecase.basic_user

import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.repository.BasicUserRepository
import kotlinx.coroutines.flow.Flow

class GetBasicUserUseCase(
    private val repository: BasicUserRepository
) {
    operator fun invoke(id: Long?): Flow<BasicUser> {
        return repository.getBasicUserById(id = id)
    }
}