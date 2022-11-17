package br.com.lighttasks.presentation.data.data_source.team

import br.com.lighttasks.presentation.data.remote.dto.teams.TeamRequest
import br.com.lighttasks.presentation.data.remote.dto.teams.TeamResponse

interface TeamRemoteDataSource {

    fun getTeamsByUser(id: Long): List<TeamResponse>

    fun getTeamsById(id: Long): TeamResponse

    fun createTeam(team: TeamRequest): TeamResponse

    fun editTeam(id: Long, team: TeamRequest): TeamResponse

    fun deleteTeam(id: Long): Unit?

}