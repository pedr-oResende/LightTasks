package br.com.lighttasks.domain.usecase.basic_user

import br.com.lighttasks.domain.repository.BasicUserRepository
import kotlinx.coroutines.flow.Flow

class AddTeamMemberUseCase(
    private val repository: BasicUserRepository
) {
    operator fun invoke(memberId: Long, teamId: Long): Flow<Unit?> {
        return repository.addTeamMember(memberId = memberId, teamId = teamId)
    }
}