package br.com.lighttasks.domain.repository

import br.com.lighttasks.data.remote.model.teams.TeamRequest
import br.com.lighttasks.data.remote.model.teams.TeamResponse
import br.com.lighttasks.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    fun getTeamsByUser(id: Long): Flow<List<TeamResponse>>

    fun getTeamsById(id: Long): Flow<Team>

    fun createTeam(team: TeamRequest): Flow<Team>

    fun editTeam(id: Long, team: TeamRequest): Flow<Team>

    fun deleteTeam(id: Long): Flow<Unit?>
}