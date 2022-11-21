package br.com.lighttasks.domain.usecase.basic_user

import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.repository.BasicUserRepository
import kotlinx.coroutines.flow.Flow

class EditBasicUserUseCase(
    private val repository: BasicUserRepository
) {
    operator fun invoke(basicUser: BasicUser): Flow<BasicUser> {
        return repository.editBasicUser(basicUser)
    }
}