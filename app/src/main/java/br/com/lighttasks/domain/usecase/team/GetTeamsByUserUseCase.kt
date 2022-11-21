package br.com.lighttasks.domain.usecase.team

import br.com.lighttasks.domain.model.Team
import br.com.lighttasks.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow

class GetTeamsByUserUseCase(
    private val repository: TeamRepository
) {
    operator fun invoke(id: Long): Flow<List<Team>> {
        return repository.getTeamsByUser(id = id)
    }
}