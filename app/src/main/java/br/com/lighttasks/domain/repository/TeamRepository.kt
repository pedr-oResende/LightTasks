package br.com.lighttasks.domain.repository

import br.com.lighttasks.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    fun getTeamsByUser(id: Long): Flow<List<Team>>

    fun getTeamById(id: Long): Flow<Team>

    fun createTeam(team: Team): Flow<Team>

    fun editTeam(team: Team): Flow<Team>

    fun deleteTeam(id: Long): Flow<Unit?>
}