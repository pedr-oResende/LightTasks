package br.com.lighttasks.domain.usecase.team

import br.com.lighttasks.domain.model.Team
import br.com.lighttasks.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow

class CreateTeamUseCase(
    private val repository: TeamRepository
) {
    operator fun invoke(team: Team): Flow<Team> {
        return repository.createTeam(team = team)
    }
}