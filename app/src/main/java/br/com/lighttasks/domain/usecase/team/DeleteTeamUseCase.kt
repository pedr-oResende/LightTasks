package br.com.lighttasks.domain.usecase.team

import br.com.lighttasks.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow

class DeleteTeamUseCase(
    private val repository: TeamRepository
) {
    operator fun invoke(id: Long): Flow<Unit?> {
        return repository.deleteTeam(id = id)
    }
}